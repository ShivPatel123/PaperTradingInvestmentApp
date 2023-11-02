package com.example.as1.screens;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.as1.Controllers.ScrollAdapter;
import com.example.as1.Controllers.ScrollStockCard;
import com.example.as1.Controllers.Stock;
import com.example.as1.Controllers.StockPurchased;
import com.example.as1.Controllers.User;
import com.example.as1.ExternalControllers.VolleySingleton;
import com.example.as1.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PortfolioPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_portfolio_page);
        RecyclerView recyclerView = findViewById(R.id.stock_scroll);

        List<StockPurchased> stockPurchasedList;
        ArrayList<ScrollStockCard> stockCardArrayList= new ArrayList<ScrollStockCard>();

        //get all user purchased stocks
        //TODO: get all stocks from global user
        User testUser = new User(4, 7765,"Skyler", "sky@iastate.edu", "yup", "Skyler", "SkylersPassword");

        //get user stocks from server
        stockPurchasedList = getAllUserStocks(this.getApplicationContext(), testUser);

        //update local/global user
        testUser.setStocks(stockPurchasedList);

        for(StockPurchased stockPurchased : stockPurchasedList){
            stockCardArrayList.add(new ScrollStockCard(stockPurchased.getStock().getCompany().toString(),
                    stockPurchased.getNumPurchased(), (int) stockPurchased.getCostPurchase(), null));
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ScrollAdapter(this.getApplicationContext(), stockCardArrayList));

    }//onCreate

    public List<StockPurchased> getAllUserStocks(Context context, User user) {
        //TODO: get URL right -- check with backend to make sure it hasnt changed
        String URL_JSON_OBJECT = "http://10.90.75.130:8080/user/" + user.getId();
        List<StockPurchased> stockPurchasedList = new ArrayList<StockPurchased>() {};

        //Create new request
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                URL_JSON_OBJECT,
                null,
                response -> {
                    try {
                        //parse stocks from json array
                        JSONArray responseJSONArray = response.getJSONArray("stocks");
                        JSONObject object;
                        for(int i = 0; i < responseJSONArray.length(); i++){
                            //transfer response data to stocklistPurchased
                            object = responseJSONArray.getJSONObject(i);
                            StockPurchased stockPurchased = new StockPurchased();

                            stockPurchased.setId(object.getLong("id"));
                            stockPurchased.setUser((User) object.get("user"));
                            stockPurchased.setStock((Stock) object.get("stock"));
                            stockPurchased.setNumPurchased(object.getInt("numPurchased"));
                            stockPurchased.setCostPurchase(object.getDouble("costPurchase"));
                            stockPurchased.setSinglePrice(object.getDouble("singlePrice"));
                            stockPurchasedList.add(i, stockPurchased);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> Log.i("parse error ", "getAllUserStocks: "+ error.getMessage())) {};

        // Adding request to request queue
        VolleySingleton.getInstance(context.getApplicationContext()).addToRequestQueue(request);
        return stockPurchasedList;
    }
}//end
