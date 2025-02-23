package com.example.navviewfe.Screens;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.GetChars;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.navviewfe.Controllers.RecycleViews.StockPreviewScrollAdapter;
import com.example.navviewfe.Controllers.RecycleViews.StockPreviewScrollCard;
import com.example.navviewfe.Controllers.RecycleViews.StockScrollAdapter;
import com.example.navviewfe.Controllers.RecycleViews.StockScrollCard;
import com.example.navviewfe.Controllers.RecycleViews.UserScrollAdapter;
import com.example.navviewfe.Controllers.RecycleViews.UserScrollCard;
import com.example.navviewfe.Controllers.Stock;
import com.example.navviewfe.Controllers.StockPurchased;
import com.example.navviewfe.Controllers.User;
import com.example.navviewfe.ExternalControllers.VolleySingleton;
import com.example.navviewfe.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AdminDashboardPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_dash_page);

        //Update Stocks
        Button updateStocks = findViewById(R.id.updateStocks_Adminbtn);
        updateStocks.setOnClickListener(view -> {
            getAllStocks(this.getApplicationContext());
        });

        //Create Stock
        Button createStock = findViewById(R.id.createStock_Adminbtn);
        createStock.setOnClickListener(view -> {
            Intent intent = new Intent(AdminDashboardPage.this, CreateStock.class);
            startActivity(intent);
        });

        //Back Button
        Button back = findViewById(R.id.back_AdminDash);
        back.setOnClickListener(view -> {
            Intent intent = new Intent(AdminDashboardPage.this, NavPage.class);
            startActivity(intent);
        });

        //Initialize recycler view (user)
        RecyclerView recyclerView = findViewById(R.id.user_scroll);
        ArrayList<UserScrollCard> userCardArrayList = new ArrayList<>();
        userCardArrayList.add(new UserScrollCard("noname", -1, 'u'));
        UserScrollAdapter userScrollAdapter = new UserScrollAdapter(this, userCardArrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        //set recycler view
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(userScrollAdapter);
        getAllUsers(this.getApplicationContext());

        //Initialize recycler view (stock)
        RecyclerView stockRecyclerView = findViewById(R.id.stockPreview_scroll);
        ArrayList<StockPreviewScrollCard> stockCardArrayList = new ArrayList<>();
        stockCardArrayList.add(new StockPreviewScrollCard("noname", 0, 0));
        StockPreviewScrollAdapter StockPreviewScrollAdapter = new StockPreviewScrollAdapter(this, stockCardArrayList);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        //Set recycler view
        stockRecyclerView.setLayoutManager(linearLayoutManager2);
        stockRecyclerView.setAdapter(StockPreviewScrollAdapter);
        getAllStocks(this.getApplicationContext());

    }


    //get all users from server and parse into recycle view
    public void getAllUsers(Context context) {
        String URL_JSON_OBJECT = "http://10.90.75.130:8080/users";
        //http://coms-309-051.class.las.iastate.edu:8080/user/

        ArrayList<UserScrollCard> userCardArrayList= new ArrayList<>();
        RecyclerView recyclerView = findViewById(R.id.user_scroll);

        //Create new request
        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET,
                URL_JSON_OBJECT,
                null,
                response -> {
                    Log.i(" full response", "getAllUsers: " + response.toString());

                    JSONObject object;

                    for(int i = 0; i < response.length(); i++) {
                        try {
                            object = (JSONObject) response.get(i);
                            // JSONObject stockObj = (JSONObject) object.get("user");
                            //User userIN = new User();

                            //parse relevant info
                            String username = object.getString("name");
                            long id = object.getLong("id");
                            char priv = object.getString("privilege").toCharArray()[0];

                            //add to arraylist to be displayed in recycle view
                            userCardArrayList.add(new UserScrollCard(username, id, priv));

                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }

                    }
                    //Initialize recycler view
                    UserScrollAdapter userScrollAdapter = new UserScrollAdapter(this, userCardArrayList);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

                    //Set recycler view
                    recyclerView.setLayoutManager(linearLayoutManager);
                    recyclerView.setAdapter(userScrollAdapter);
                },

                error -> Log.i("Error ", "getAllUsers: "+ error.getMessage())) {};

        // Adding request to request queue
        VolleySingleton.getInstance(context.getApplicationContext()).addToRequestQueue(request);
    }

    //get all users from server and parse into recycle view
    public void getAllStocks(Context context) {
        String URL_JSON_OBJECT = "http://10.90.75.130:8080/stocks";
        //http://coms-309-051.class.las.iastate.edu:8080/user/

        ArrayList<StockPreviewScrollCard> stockCardArrayList= new ArrayList<>();
        RecyclerView recyclerView = findViewById(R.id.stockPreview_scroll);

        //Create new request
        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET,
                URL_JSON_OBJECT,
                null,
                response -> {
                    Log.i(" full response", "getAllStocks: " + response.toString());

                    JSONObject object;

                    for(int i = 0; i < response.length(); i++) {
                        try {
                            object = (JSONObject) response.get(i);
                            // JSONObject stockObj = (JSONObject) object.get("user");
                            //User userIN = new User();

                            //parse relevant info
                            String company = object.getString("company");
                            long id = object.getLong("id");
                            double currValue = object.getDouble("currValue");

                            //add to arraylist to be displayed in recycle view
                            stockCardArrayList.add(new StockPreviewScrollCard(company, currValue, id));

                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }

                    }
                    //Initialize recycler view
                    StockPreviewScrollAdapter stockScrollAdapter = new StockPreviewScrollAdapter(this, stockCardArrayList);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

                    //Set recycler view
                    recyclerView.setLayoutManager(linearLayoutManager);
                    recyclerView.setAdapter(stockScrollAdapter);
                },

                error -> Log.i("Error ", "getAllStocks: "+ error.getMessage())) {};

        // Adding request to request queue
        VolleySingleton.getInstance(context.getApplicationContext()).addToRequestQueue(request);
    }




}
