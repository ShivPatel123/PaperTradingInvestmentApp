package com.example.navviewfe.Screens;

import static android.app.PendingIntent.getActivity;

import static com.example.navviewfe.Controllers.User.getInstance;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.navviewfe.Controllers.RecycleViews.StockPreviewScrollAdapter;
import com.example.navviewfe.Controllers.RecycleViews.StockPreviewScrollCard;
import com.example.navviewfe.Controllers.RecycleViews.StockScrollAdapter;
import com.example.navviewfe.Controllers.RecycleViews.StockScrollCard;
import com.example.navviewfe.Controllers.StockPurchased;
import com.example.navviewfe.Controllers.User;
import com.example.navviewfe.ExternalControllers.VolleySingleton;
import com.example.navviewfe.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class StockList extends AppCompatActivity {
    private String URL_JSON_OBJECT = "http://coms-309-051.class.las.iastate.edu:8080/stock/";
    public String adapList[] = {"Please Load Stocks:"};
    public ArrayList<String> stockListNames = new ArrayList<String>();
    private ListView replacement;
    private ListView replacement2;
    public ArrayList<String> mainStockList = new ArrayList<String>();
    public ArrayList<Integer> mainStockPrice = new ArrayList<Integer>();
    public ArrayList<Integer> mainStockID = new ArrayList<Integer>();
    public ArrayList<String> mainStockSymbol = new ArrayList<String>();

    public View view;
    public TextView t;
    public Button allStocks;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stock_list_page);

        //get all stocks from server
        getAllStocks(this.getApplicationContext());

        Button back_Btn = findViewById(R.id.back_StockListBtn);
        back_Btn.setOnClickListener(view -> {
            User user = getInstance();
            Intent intent;
            if(user.getUsername().equals(null)){
                intent = new Intent(StockList.this, StartPage.class);
            }
            else{
                intent = new Intent(StockList.this, NavPage.class);
            }
            startActivity(intent);

        });
    }

    public void getAllStocks(Context context) {
        String URL_JSON_OBJECT = "http://10.90.75.130:8080/stocks";
        //http://coms-309-051.class.las.iastate.edu:8080/user/

        ArrayList<StockScrollCard> stockCardArrayList= new ArrayList<>();
        RecyclerView recyclerView = findViewById(R.id.stockList_scroll);

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
                            String name = object.getString("company");
                            name += " " + object.getString("symbol");
                            int prevDay = (int) object.getDouble("prevDayChange");
                            int stockPrice = (int) object.getDouble("currValue");

                            //add to arraylist to be displayed in recycle view
                            //TODO
                            stockCardArrayList.add(new StockScrollCard(name, prevDay, stockPrice, 0, "none"));

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

                error -> Log.i("Error ", "getAllStocks: "+ error.getMessage())) {};

        // Adding request to request queue
        VolleySingleton.getInstance(context.getApplicationContext()).addToRequestQueue(request);
    }

}
