package com.example.as1.screens;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
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
import com.example.as1.Controllers.ScrollAdapter;
import com.example.as1.Controllers.ScrollStockCard;
import com.example.as1.Controllers.Stock;
import com.example.as1.Controllers.StockPurchased;
import com.example.as1.Controllers.User;
import com.example.as1.ExternalControllers.VolleySingleton;
import com.example.as1.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PortfolioPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_portfolio_page);
        RecyclerView recyclerView = findViewById(R.id.stock_scroll);

        ArrayList<ScrollStockCard> stockCardArrayList= new ArrayList<>();
        List<StockPurchased> stockPurchasedList;

        //get all user purchased stocks
        User getGlobal = getInstance();
        getGlobal = getUserData(this.getApplicationContext(), getGlobal);
        if(getGlobal.getId() <= 0) getGlobal.setId(1);

        //get user stocks from server
        stockPurchasedList = getAllUserStocks(this.getApplicationContext(), getGlobal);
        Log.i("end of get request", "Stock Purchased List:" + stockPurchasedList);

        //update local/global user
        getGlobal.setStocks(stockPurchasedList);

        //Add stock cards for each stock in user's stock purchased list
        for(StockPurchased stockPurchased : stockPurchasedList){
            stockCardArrayList.add(new ScrollStockCard(stockPurchased.getStock().getCompany().toString(),
                    stockPurchased.getNumPurchased(), (int) stockPurchased.getCostPurchase(), null));
        }

        //dynamic scroll view for putting cards on screen
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ScrollAdapter(this.getApplicationContext(), stockCardArrayList));

        //back to main button
        Button backHome_btn = findViewById(R.id.backHome_PortfolioBtn);
        backHome_btn.setOnClickListener(view -> {
            Intent intent = new Intent(PortfolioPage.this, NavPage.class);
            startActivity(intent);
        });

    }//onCreate

//    private class AsyncGetReq extends AsyncTask<User, Void, Void>{
//        List<StockPurchased> stockPurchasedList;
//        @Override
//        protected Void doInBackground(User... users) {
//            stockPurchasedList = getAllUserStocks(getApplicationContext(), users[0]);
//            Log.i("Print stock List", "doInBackground: " + stockPurchasedList.toString());
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Void result) {
//            Log.i("background", "onPostExecute: " + stockPurchasedList.toString());
//        }
//    }

    public List<StockPurchased> getAllUserStocks(Context context, User user) {
        String URL_JSON_OBJECT = "http://10.90.75.130:8080/user/" + user.getId();
        //http://coms-309-051.class.las.iastate.edu:8080/user/
        List<StockPurchased> stockPurchasedList = new ArrayList<StockPurchased>() {};

        //Create new request
        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET,
                URL_JSON_OBJECT,
                null,
                response -> {
                        Log.i("response", "getAllUserStocks: " + response.toString());

                        StockPurchased stockPurchased = new StockPurchased();
                        JSONObject object;

                        for(int i = 0; i < response.length(); i++) {
                            try {
                                object = (JSONObject) response.get(i);
                                Log.i("Response at " + i, " : " + object);
                                stockPurchased.setId(object.getLong("id"));

                                //Dont need to get user here
                                //Do need stock tho
                                JSONObject stockObj = (JSONObject) object.get("stock");
                                Stock stockIN = new Stock();
                                stockIN.setPrevDayChange(stockObj.getDouble("prevDayChange"));
                                stockIN.setCurrValue(stockObj.getDouble("currValue"));
                                stockIN.setCompany(stockObj.getString("company"));
                                stockIN.setId(stockObj.getLong("id"));
                                stockIN.setSymbol(stockObj.getString("symbol"));
                                stockPurchased.setStock(stockIN);

                                //Done parsing stock, continue parsing response
                                stockPurchased.setCostPurchase(object.getDouble("costPurchase"));
                                stockPurchased.setNumPurchased(object.getInt("numPurchased"));
                                stockPurchased.setSinglePrice(object.getDouble("singlePrice"));

                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                            stockPurchasedList.add(stockPurchased);
                            Log.i("loop", "stockPurchased List after loop "+ i + " " + stockPurchasedList.toString());
                            }
                        },

                error -> Log.i("parse error ", "getAllUserStocks: "+ error.getMessage())) {};

        // Adding request to request queue
        VolleySingleton.getInstance(context.getApplicationContext()).addToRequestQueue(request);
        return stockPurchasedList;
    }

    public User getUserData(Context context, User user) {
        String URL_JSON_OBJECT = "http://coms-309-051.class.las.iastate.edu:8080/userByName/" + user.getUsername();

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
                        String username = response.getString("username");
                        String password = response.getString("password");

                        // Populate text views with the parsed data
                        user.setName(name);
                        user.setEmail(email);
                        user.setId(Integer.parseInt(id));
                        user.setDob(dob);
                        user.setMoney(Double.parseDouble(money));
                        user.setUsername(username);
                        user.setPassword(password);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> Log.i("error msg", "getUserData: " + error.getMessage())) {
        };

        // Adding request to request queue
        VolleySingleton.getInstance(context.getApplicationContext()).addToRequestQueue(request);
        return user;
    }
}//end
