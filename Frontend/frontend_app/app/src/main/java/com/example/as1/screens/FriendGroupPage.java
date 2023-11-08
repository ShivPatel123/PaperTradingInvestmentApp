package com.example.as1.screens;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.as1.ExternalControllers.VolleySingleton;
import com.example.as1.R;

public class FriendGroupPage extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friend_group_page);

        //back to main button
        Button backHome_btn = findViewById(R.id.backHome_FriendGroupBtn);
        backHome_btn.setOnClickListener(view -> {
            Intent intent = new Intent(FriendGroupPage.this, NavPage.class);
            startActivity(intent);
        });

        //to friends chat button
        Button toChat_btn = findViewById(R.id.toChat_friendGroupBtn);
        toChat_btn.setOnClickListener(view -> {
            Intent intent = new Intent(FriendGroupPage.this, FriendsChatPage.class);
            startActivity(intent);
        });

        TextView groupDisplay = findViewById(R.id.groupDisplay);
        getGroupMembers(this.getApplicationContext());

    }//onCreate

    public void getGroupMembers(Context context) {
        String groupName = "StockGroup1";
        String URL_JSON_OBJECT = "http://coms-309-051.class.las.iastate.edu:8080/friendgroup/" + groupName;

        //Create new request
        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET,
                URL_JSON_OBJECT,
                null,
                response -> {
                    Log.i("response", "getAllGroupMembers: " +  response.toString());

                },
                error -> Log.i("Error Message for ", "getGroupMembers: " + error.getMessage().toString())) {
        };

        // Adding request to request queue
        VolleySingleton.getInstance(context.getApplicationContext()).addToRequestQueue(request);
    }

}//end
