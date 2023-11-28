package com.example.as1.screens;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.as1.Controllers.RecycleViews.StockScrollAdapter;
import com.example.as1.Controllers.RecycleViews.StockScrollCard;
import com.example.as1.Controllers.StockPurchased;
import com.example.as1.Controllers.User;
import com.example.as1.ExternalControllers.VolleySingleton;
import com.example.as1.R;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class NavPage extends AppCompatActivity {
    ImageButton toStockList_btn, toPortfolio_btn, toSingleStock_btn;
    Button toTutorials_btn;
    ImageButton toProfile_btn,toGroup_btn,toAdminDash_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_page);

        //Get global user data for get request
        User getGlobal = User.getInstance();
        User.updateInstance(getUserData(this.getApplicationContext(), getGlobal));

        getAllUserStocks(this.getApplicationContext(), getGlobal);
        getUserData(this.getApplicationContext(), getGlobal);

        TextView welcomeTxt = findViewById(R.id.navpage_welcomeTxt);
        welcomeTxt.setText("Welcome, " + getGlobal.getName() + "! Navigate Here");

        //View Profile Page
        toProfile_btn = findViewById(R.id.viewProfile_navBtn);
        toProfile_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(NavPage.this, ProfilePage.class);
                startActivity(intent);
            }
        });

        //button to group page
        toGroup_btn = findViewById(R.id.group_Main_btn);
        toGroup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(NavPage.this, GroupPage.class);
                startActivity(intent);
            }
        });

        //button to portfolio page
        toPortfolio_btn = findViewById(R.id.portfolio_navBtn);
        toPortfolio_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(NavPage.this, PortfolioPage.class);
                startActivity(intent);
            }
        });

        //button to stock list page
        toStockList_btn = findViewById(R.id.stockList_navBtn);
        toStockList_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(NavPage.this, StockList.class);
                startActivity(intent);
            }
        });

        //button to single stock page
        toSingleStock_btn = findViewById(R.id.singleStock_navBtn);
        toSingleStock_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(NavPage.this, StockPage.class);
                startActivity(intent);
            }
        });

        //button to admin page
        toAdminDash_btn = findViewById(R.id.admin_main_btn);
        toAdminDash_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(NavPage.this, AdminDashboardPage.class);
                startActivity(intent);
            }
        });


        //button to tutorials page
        toTutorials_btn = findViewById(R.id.tutorial_Main_btn);
        toTutorials_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(NavPage.this, TutorialsPage.class);
                startActivity(intent);
            }
        });

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
                        char priv = response.getString("privilege").toCharArray()[0];

                        // Populate text views with the parsed data
                        user.setName(name);
                        user.setEmail(email);
                        user.setId(Integer.parseInt(id));
                        user.setDob(dob);
                        user.setMoney(Double.parseDouble(money));
                        user.setUsername(username);
                        user.setPassword(password);
                        user.setPrivilege(priv);

                        TextView yourMoney_Display = findViewById(R.id.yourMoney_Display);
                        yourMoney_Display.setText("$" + money);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error ->  Log.i("error", "getUserData: " + error)) {
        };

        // Adding request to request queue
        VolleySingleton.getInstance(context.getApplicationContext()).addToRequestQueue(request);
        return user;
    }
    public void getAllUserStocks(Context context, User user) {
        String URL_JSON_OBJECT = "http://10.90.75.130:8080/user/" + user.getId();
        TextView stockChange_txt = findViewById(R.id.stockChange_txt);
        //Create new request
        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET,
                URL_JSON_OBJECT,
                null,
                response -> {
                    double changeTotal = 0;
                    Log.i(" full response", "getAllUserStocks: " + response.toString());
                    for(int i = 0; i < response.length(); i++) {
                        try {
                        JSONObject object = (JSONObject) response.get(i);
                        JSONObject stockObj = (JSONObject) object.get("stock");
                        changeTotal += stockObj.getDouble("prevDayChange");
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    double stockPercentNum = (changeTotal / user.getMoney()) * 100;
                    String stockPercent = String.format("%.3f", stockPercentNum);
                    stockChange_txt.setText("" + changeTotal + " || " + stockPercent + "%");

                    ImageView fundsImage = findViewById(R.id.stock_ImageView1);
                    if(changeTotal < 0){
                        fundsImage.setImageResource(R.drawable.stockredarrow);
                    }
                    else{
                        fundsImage.setImageResource(R.drawable.greenarrow);
                    }
                },
                error -> Log.i("parse error ", "getAllUserStocks: "+ error.getMessage())) {};

        // Adding request to request queue
        VolleySingleton.getInstance(context.getApplicationContext()).addToRequestQueue(request);
    }

}

