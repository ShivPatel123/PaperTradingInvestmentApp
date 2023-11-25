package com.example.as1.screens;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.as1.Controllers.StockPurchased;
import com.example.as1.Controllers.User;
import com.example.as1.ExternalControllers.VolleySingleton;
import com.example.as1.R;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class NavPage extends AppCompatActivity {
    Button toStockList_btn, toTutorials_btn, toPortfolio_btn, toSingleStock_btn;
    ImageButton toProfile_btn,toGroup_btn,toAdminDash_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_page);

        //Get global user data for get request
        User getGlobal = User.getInstance();
        User.updateInstance(getUserData(this.getApplicationContext(), getGlobal));

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

}

