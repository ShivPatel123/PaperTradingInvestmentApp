package com.example.as1.screens;

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
import com.example.as1.Controllers.RecycleViews.GroupMemScrollCard;
import com.example.as1.Controllers.RecycleViews.GroupScrollAdapter;
import com.example.as1.Controllers.StockPurchased;
import com.example.as1.Controllers.User;
import com.example.as1.ExternalControllers.VolleySingleton;
import com.example.as1.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GroupAdminPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.group_admin_page);

        //Back
        Button back = findViewById(R.id.back_groupAdminBtn);
        back.setOnClickListener(view -> {
            Intent intent = new Intent(GroupAdminPage.this, GroupPage.class);
            startActivity(intent);
        });

        //Create new Group
        Button createGroup = findViewById(R.id.createGroup_Adminbtn);
        createGroup.setOnClickListener(view -> {
            Intent intent = new Intent(GroupAdminPage.this, CreateStock.class);
            startActivity(intent);
        });

        //Initialize recycler view (user)
        RecyclerView recyclerView = findViewById(R.id.groupUser_scroll);
        ArrayList<GroupMemScrollCard> GroupCardArray = new ArrayList<>();
        GroupCardArray.add(new GroupMemScrollCard("noname", "noInputs", 0,'u', 0.0, 0 ));
        GroupScrollAdapter groupScrollAdapter = new GroupScrollAdapter(this, GroupCardArray);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        //set recycler view
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(groupScrollAdapter);
        getGroupMembers(this.getApplicationContext(), "StockGroup1");
    }

//TODO: Fix "no adapter connected error and add recycler view cards for groups"
    public void getGroupMembers(Context context, String groupName) {
        String URL_JSON_OBJECT = "http://coms-309-051.class.las.iastate.edu:8080/friendgroup/" + groupName;

        ArrayList<GroupMemScrollCard> GroupMemCardArrayList= new ArrayList<>();
        RecyclerView recyclerView = findViewById(R.id.groupUser_scroll);

        //Create new request
        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET,
                URL_JSON_OBJECT,
                null,
                response -> {
                    Log.i(" full response", "getAllGroupMembers: " + response.toString());

                    JSONObject object;

                    for(int i = 0; i < response.length(); i++) {
                        try {
                            object = (JSONObject) response.get(i);

                            //parse relevant info
                            String username = object.getString("username");
                            String name = object.getString("name");
                            int id = object.getInt("id");
                            char priv = object.getString("privilege").toCharArray()[0];
                            int money = object.getInt("money");

                            List<StockPurchased> stocks = (List<StockPurchased>) object.get("stocks");
                            int numStocks = stocks.size() - 1;
                            //add to arraylist to be displayed in recycle view
                            GroupMemCardArrayList.add(new GroupMemScrollCard(name,username, id, priv, money, numStocks));

                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }

                    }
                    //Initialize recycler view
                    GroupScrollAdapter groupScrollAdapter = new GroupScrollAdapter(this, GroupMemCardArrayList);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

                    //Set recycler view
                    recyclerView.setLayoutManager(linearLayoutManager);
                    recyclerView.setAdapter(groupScrollAdapter);
                },

                error -> Log.i("Error ", "getAllGroupMembers: "+ error.getMessage())) {};

        // Adding request to request queue
        VolleySingleton.getInstance(context.getApplicationContext()).addToRequestQueue(request);
    }
}