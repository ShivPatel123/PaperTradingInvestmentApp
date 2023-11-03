package com.example.as1.screens;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import static com.example.as1.Controllers.User.getInstance;
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
        ArrayList<ScrollStockCard> stockCardArrayList= new ArrayList<>();

        //get all user purchased stocks
        User getGlobal = getInstance();
        getGlobal = getUserData(this.getApplicationContext(), getGlobal);
        //get user stocks from server
        stockPurchasedList = getAllUserStocks(this.getApplicationContext(), getGlobal);
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
            Intent intent = new Intent(PortfolioPage.this, MainPage.class);
            startActivity(intent);
        });

    }//onCreate

    public List<StockPurchased> getAllUserStocks(Context context, User user) {
        String URL_JSON_OBJECT = "http://coms-309-051.class.las.iastate.edu:8080/user/" + user.getId();
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
                        JSONArray object;
                        for(int i = 0; i < responseJSONArray.length(); i++){
                            //each index in response array is a JSON array
                            object = responseJSONArray.getJSONArray(i);
                            StockPurchased stockPurchased = new StockPurchased();

                            //numbers are the indexes of the object array
                            stockPurchased.setId(object.getLong(0));
                            //parse array into user
                            JSONArray responseUser = object.getJSONArray(1);
                            JSONObject parseUser;
                            User inputUser = new User();
                            for(int j = 0; j < responseUser.length(); i++) {
                                parseUser = responseUser.getJSONObject(j);
                                inputUser.setId(parseUser.getLong("id"));
                                inputUser.setMoney(parseUser.getDouble("money"));
                                inputUser.setPassword(parseUser.getString("password"));
                                inputUser.setUsername(parseUser.getString("username"));
                                inputUser.setName(parseUser.getString("name"));
                                inputUser.setDob(parseUser.getString("dob"));
                                inputUser.setUsername(parseUser.getString("email"));
                            }
                            stockPurchased.setUser(inputUser);

                            //parse stock array into stock
                            stockPurchased.setStock((Stock) object.get(2));

                            stockPurchased.setNumPurchased(object.getInt(3));
                            stockPurchased.setCostPurchase(object.getDouble(4));
                            stockPurchased.setSinglePrice(object.getDouble(5));

//                            Stock stock = new Stock();
//                            stock.setId((Long) object.get(Integer.parseInt("id")));
//
//                            stockPurchased.setId(object.getLong(Integer.parseInt("id")));
//                            stockPurchased.setUser((User) object.get(Integer.parseInt("user")));
//                            stockPurchased.setStock((Stock) object.get("stock"));
//                            stockPurchased.setNumPurchased(object.getInt("numPurchased"));
//                            stockPurchased.setCostPurchase(object.getDouble("costPurchase"));
//                            stockPurchased.setSinglePrice(object.getDouble("singlePrice"));
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
                        //TODO parse arraylist to get stock list

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
