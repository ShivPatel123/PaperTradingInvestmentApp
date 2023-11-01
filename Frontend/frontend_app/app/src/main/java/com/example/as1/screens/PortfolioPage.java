package com.example.as1.screens;

import android.content.Context;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.as1.Controllers.StockAdapter;
import com.example.as1.Controllers.StockPurchased;
import com.example.as1.Controllers.User;
import com.example.as1.ExternalControllers.VolleySingleton;
import com.example.as1.R;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class PortfolioPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_portfolio_page);

        List<StockPurchased> stockPurchasedList;

        //get all user purchased stocks
        //TODO: get all stocks from global user
        User testUser = new User(4, 7765,"Skyler", "sky@iastate.edu", "yup", "Skyler", "SkylersPassword");
        stockPurchasedList = testUser.getStocks();
        //parse: for each element in <>
        //for(StockPurchased p : stockPurchasedList){};
        //stockPurchasedList.add(i, element);

        RecyclerView recyclerView = findViewById(R.id.stock_scroll);
        recyclerView.setAdapter(new StockAdapter(this.getApplicationContext(), stockPurchasedList));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }//onCreate

//    public StockPurchased getUserStocks(Context context, User user) {
//        String URL_JSON_OBJECT = "http://10.90.75.130:8080/stocks/".concat(String.valueOf(user.getId()));
//        int confirm = getResources().getColor(R.color.greenConfirm);
//        TextView confirm_display = findViewById(R.id.confirmWindow);
//
//        //Create new request
//        JsonObjectRequest request = new JsonObjectRequest(
//                Request.Method.GET,
//                URL_JSON_OBJECT,
//                null,
//                response -> {
//                    try {
//
//                        //get all user purchased stocks
//                        //parse: for each element in <>
//                        //stockPurchasedList.add(i, element);
//
//                        // Parse JSON object data
//                        String name = response.getString("name");
//                        String email = response.getString("email");
//                        String id = response.getString("id");
//                        String dob = response.getString("dob");
//                        String money = response.getString("money");
//                        String numStocks = response.getString("numStocks");
//                        String username = response.getString("username");
//                        String password = response.getString("password");
//
//                        // Populate text views with the parsed data
//                        user.setName(name);
//                        user.setEmail(email);
//                        user.setId(Integer.parseInt(id));
//                        user.setDob(dob);
//                        user.setMoney(Double.parseDouble(money));
//                        // user.setNumStocks(Integer.parseInt(numStocks));
//                        user.setUsername(username);
//                        user.setPassword(password);
//
//                        confirm_display.setTextColor(confirm);
//
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                },
//                error -> confirm_display.setText(error.getMessage())) {
//        };
//
//        // Adding request to request queue
//        VolleySingleton.getInstance(context.getApplicationContext()).addToRequestQueue(request);
//        return null;
//    }
}//end
