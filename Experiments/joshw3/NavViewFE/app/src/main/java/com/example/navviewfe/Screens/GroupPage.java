package com.example.navviewfe.Screens;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.navviewfe.Controllers.RecycleViews.GroupMemScrollCard;
import com.example.navviewfe.Controllers.RecycleViews.GroupScrollAdapter;
import com.example.navviewfe.Controllers.StockPurchased;
import com.example.navviewfe.ExternalControllers.VolleySingleton;
import com.example.navviewfe.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GroupPage extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.group_page);

        //back to main button
        Button backHome_btn = findViewById(R.id.backHome_FriendGroupBtn);
        backHome_btn.setOnClickListener(view -> {
            Intent intent = new Intent(GroupPage.this, NavPage.class);
            startActivity(intent);
        });

        //to friends chat button
        Button toChat_btn = findViewById(R.id.toChat_friendGroupBtn);
        toChat_btn.setOnClickListener(view -> {
            Intent intent = new Intent(GroupPage.this, GroupChatPage.class);
            startActivity(intent);
        });

        //to friends chat button
        Button toAdmin_btn = findViewById(R.id.toAdmin_GroupBtn);
        toAdmin_btn.setOnClickListener(view -> {
            Intent intent = new Intent(GroupPage.this, GroupAdminPage.class);
            startActivity(intent);
        });


        //Initialize recycler view (user)
        RecyclerView recyclerView = findViewById(R.id.yourGroup_recyc);
        ArrayList<GroupMemScrollCard> GroupMemCardArrayList = new ArrayList<>();
        GroupMemCardArrayList.add(new GroupMemScrollCard("NoInputs", "NoInputs", 0, 'u', 0, 0));
        GroupScrollAdapter groupScrollAdapter = new GroupScrollAdapter(this, GroupMemCardArrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        //Set recycler view
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(groupScrollAdapter);
        getGroupMembers(this.getApplicationContext(), "StockGroup1");


    }//onCreate

    public void getGroupMembers(Context context, String groupName) {
        String URL_JSON_OBJECT = "http://coms-309-051.class.las.iastate.edu:8080/friendgroup/" + groupName;

        ArrayList<GroupMemScrollCard> GroupMemCardArrayList= new ArrayList<>();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.yourGroup_recyc);

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
                            // JSONObject stockObj = (JSONObject) object.get("user");
                            //User userIN = new User();

                            //parse relevant info
                            String username = object.getString("username");
                            String name = object.getString("name");
                            int id = object.getInt("id");
                            char priv = object.getString("privilege").toCharArray()[0];
                            int money = object.getInt("money");

                            List<StockPurchased> stocks = new ArrayList<>();
                            stocks = (List<StockPurchased>) object.get("stocks");
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

}//end
