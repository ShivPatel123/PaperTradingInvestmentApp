package com.example.as1.screens;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.as1.Controllers.FriendGroup;
import com.example.as1.Controllers.Stock;
import com.example.as1.Controllers.User;
import com.example.as1.ExternalControllers.VolleySingleton;
import com.example.as1.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CreateGroup extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_group);

        Button cancel_btn = findViewById(R.id.cancel_Groupbtn);
        Button save_group = findViewById(R.id.save_Groupbtn);
        Button addUser = findViewById(R.id.addUser_btn);
        Button addLeader = findViewById(R.id.addLeader_btn);
        EditText groupName_Display = findViewById(R.id.groupName_Display);
        EditText users_Display = findViewById(R.id.users_Display);
        EditText groupLeader_Display = findViewById(R.id.groupLeader_Display);
        TextView createGroup_confirm = findViewById(R.id.createGroup_confirm);
        TextView userDisplay_response = findViewById(R.id.userDisplay_response);
        TextView leaderDisplay_response = findViewById(R.id.leaderDisplay_response);


        //Add User button
        addUser.setOnClickListener(view -> {
            userDisplay_response.setVisibility(View.INVISIBLE);
            String groupName = String.valueOf(groupName_Display.getText());
            String userInput = users_Display.getText().toString();
            int userID = Integer.parseInt(userInput);
            addUserToGroup(this.getApplicationContext(), groupName, (long) userID);
        });

        //Add Leader button
        addLeader.setOnClickListener(view -> {
            leaderDisplay_response.setVisibility(View.INVISIBLE);
            String groupName = groupName_Display.getText().toString();
            String leader = groupLeader_Display.getText().toString();
            int leaderID = Integer.parseInt(leader);
            User global = User.getInstance();
            setLeader(this.getApplicationContext(), groupName, global.getId(), (long) leaderID);
        });

        //cancel button
        cancel_btn.setOnClickListener(view -> {
            Intent intent = new Intent(CreateGroup.this, GroupAdminPage.class);
            startActivity(intent);
        });

        //save button
        save_group.setOnClickListener(view -> {
            createGroup_confirm.setVisibility(View.INVISIBLE);
            String groupName = String.valueOf(groupName_Display.getText());
            User global = User.getInstance();
            createFriendGroup(this.getApplicationContext() ,groupName, global.getId());
        });
    }

    //JSON Request
    public void addUserToGroup(Context context, String groupName, long id) {
        String URL_JSON_OBJECT = "http://coms-309-051.class.las.iastate.edu:8080/friendgroup/" + groupName + "/" + id;
        TextView userDisplay_response = findViewById(R.id.userDisplay_response);
        EditText users_Display = findViewById(R.id.users_Display);
        //Create new request
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.PUT,
                URL_JSON_OBJECT,
                null,
                response -> {
                    if(response.equals("{\"message\":\"success\"}")){
                        userDisplay_response.setVisibility(View.VISIBLE);
                        userDisplay_response.setText("User ID " + id + " added successfilly");
                        users_Display.setText("");
                    } else if (response.equals("{\"message\":\"failure\"}")) {
                        userDisplay_response.setVisibility(View.VISIBLE);
                        userDisplay_response.setText("User ID " + id + " FAILED to add");
                        users_Display.setText("");
                    } else{
                        Log.i("unexpected response", "addUserToGroup: " + response.toString());
                    }
                },
                error -> Log.i("error msg", "addUserToGroup: " + error.getMessage())) {
        };
        // Adding request to request queue
        VolleySingleton.getInstance(context.getApplicationContext()).addToRequestQueue(request);
    }

    //JSON Request
    public void setLeader(Context context, String groupName, long uid, long lid) {
        String URL_JSON_OBJECT = "http://coms-309-051.class.las.iastate.edu:8080/friendgroup/setnewleader/" + groupName + "/" + uid + "/" + lid;
        TextView leaderDisplay_response = findViewById(R.id.leaderDisplay_response);
        EditText groupLeader_Display = findViewById(R.id.groupLeader_Display);

        //Create new request
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.PUT,
                URL_JSON_OBJECT,
                null,
                response -> {
                    if(response.equals("{\"message\":\"success\"}")){
                        leaderDisplay_response.setVisibility(View.VISIBLE);
                        leaderDisplay_response.setText("Leader ID " + lid + " set successfilly");
                        groupLeader_Display.setText("");
                    } else if (response.equals("{\"message\":\"failure\"}")) {
                        leaderDisplay_response.setVisibility(View.VISIBLE);
                        leaderDisplay_response.setText("Leader ID " + lid + " FAILED to set");
                        groupLeader_Display.setText("");
                    } else{
                        Log.i("unexpected response", "setLeader: " + response);
                    }
                },
                error -> Log.i("error msg", "setLeader: " + error.getMessage())) {
        };
        // Adding request to request queue
        VolleySingleton.getInstance(context.getApplicationContext()).addToRequestQueue(request);
    }

    //JSON Request
    public void createFriendGroup(Context context, String groupName, long id) {
        String URL_JSON_OBJECT = "http://coms-309-051.class.las.iastate.edu:8080/friendgroup/" + groupName + "/" + id;
        TextView createGroup_confirm = findViewById(R.id.createGroup_confirm);
        //Create new request
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                URL_JSON_OBJECT,
                null,
                response -> {
                    createGroup_confirm.setVisibility(View.VISIBLE);
                    createGroup_confirm.setText("Group Created Successfully");

                },
                error -> Log.i("error msg", "createGroup: " + error.getMessage())) {
        };
        // Adding request to request queue
        VolleySingleton.getInstance(context.getApplicationContext()).addToRequestQueue(request);
    }

}
