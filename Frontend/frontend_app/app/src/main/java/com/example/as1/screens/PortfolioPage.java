package com.example.as1.screens;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import static com.example.as1.Controllers.User.getInstance;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.as1.Controllers.RecycleViews.StockScrollAdapter;
import com.example.as1.Controllers.RecycleViews.StockScrollCard;
import com.example.as1.Controllers.Stock;
import com.example.as1.Controllers.StockPurchased;
import com.example.as1.Controllers.User;
import com.example.as1.ExternalControllers.VolleySingleton;
import com.example.as1.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PortfolioPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_portfolio_page);

        //Initialize recycler view
        RecyclerView recyclerView = findViewById(R.id.stock_scroll);
        ArrayList<StockScrollCard> stockCardArrayList= new ArrayList<>();
        stockCardArrayList.add(new StockScrollCard("noname", -1, -1));
        StockScrollAdapter stockScrollAdapter = new StockScrollAdapter(this, stockCardArrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        //Set recycler view
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(stockScrollAdapter);

        //get id from global user
        User getGlobal = getInstance();
        getUserData(this.getApplicationContext());
        //set id to 1 for testing/display purposes in case getUserData doesnt work
        if(getGlobal.getId() <= 0) getGlobal.setId(1);

        //get user stocks from server
        getAllUserStocks(this.getApplicationContext(), getGlobal);

        //TODO: update local/global? Is stockPurchasedList needed locally?
        //getGlobal.setStocks(stockPurchasedList);

        //back to main button
        Button backHome_btn = findViewById(R.id.backHome_PortfolioBtn);
        backHome_btn.setOnClickListener(view -> {
            Intent intent = new Intent(PortfolioPage.this, NavPage.class);
            startActivity(intent);
        });

        //To Stock list button
        Button toStockList_btn = findViewById(R.id.stockEdit_PortfolioBtn);
        toStockList_btn.setOnClickListener(view -> {
            Intent intent = new Intent(PortfolioPage.this, StockList.class);
            startActivity(intent);
        });

    }//onCreate

    public void getAllUserStocks(Context context, User user) {
        String URL_JSON_OBJECT = "http://10.90.75.130:8080/user/" + user.getId();
        //http://coms-309-051.class.las.iastate.edu:8080/user/

        ArrayList<StockScrollCard> stockCardArrayList= new ArrayList<>();
        RecyclerView recyclerView = findViewById(R.id.stock_scroll);

        //Create new request
        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET,
                URL_JSON_OBJECT,
                null,
                response -> {
                        Log.i(" full response", "getAllUserStocks: " + response.toString());

                        StockPurchased stockPurchased = new StockPurchased();
                        JSONObject object;

                        for(int i = 0; i < response.length(); i++) {
                            try {
                                //Get next stock from response JSON array
                                object = (JSONObject) response.get(i);
                                JSONObject stockObj = (JSONObject) object.get("stock");
                                Stock stockIN = new Stock();

                                //parse relevant info
                                String stockName = stockObj.getString("company");
                                int numP = object.getInt("numPurchased");
                                int price = (int) object.getDouble("singlePrice");

                                //add to arraylist to be displayed in recycle view
                                stockCardArrayList.add(new StockScrollCard(stockName, numP, price));

                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }

                        }
                        //Initialize recycler view
                        StockScrollAdapter stockScrollAdapter = new StockScrollAdapter(this, stockCardArrayList);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

                        //Set recycler view
                        recyclerView.setLayoutManager(linearLayoutManager);
                        recyclerView.setAdapter(stockScrollAdapter);
                        },

                error -> Log.i("parse error ", "getAllUserStocks: "+ error.getMessage())) {};

        // Adding request to request queue
        VolleySingleton.getInstance(context.getApplicationContext()).addToRequestQueue(request);
    }

    public void getUserData(Context context) {
        User getGlobal = getInstance();
        String URL_JSON_OBJECT = "http://coms-309-051.class.las.iastate.edu:8080/userByName/" + getGlobal.getUsername();

        //Create new request
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                URL_JSON_OBJECT,
                null,
                response -> {
                    try {
                        // Parse JSON object data
                        String name = response.getString("name");
                        String email = response.getString("email");
                        String id = response.getString("id");
                        String dob = response.getString("dob");
                        String money = response.getString("money");

                        // Populate text views with the parsed data
                        getGlobal.setName(name);
                        getGlobal.setEmail(email);
                        getGlobal.setId(Integer.parseInt(id));
                        getGlobal.setDob(dob);
                        getGlobal.setMoney(Double.parseDouble(money));

                        Log.i("getUserData result", "getGlobal Updated if id != -1: " + getGlobal.getId());

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> Log.i("error msg", "getUserData: " + error.getMessage())) {
        };

        // Adding request to request queue
        VolleySingleton.getInstance(context.getApplicationContext()).addToRequestQueue(request);
    }
}//end
