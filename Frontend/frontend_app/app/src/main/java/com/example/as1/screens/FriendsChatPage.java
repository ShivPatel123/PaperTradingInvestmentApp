package com.example.as1.screens;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.as1.Controllers.User;
import com.example.as1.ExternalControllers.VolleySingleton;
import com.example.as1.ExternalControllers.WebSocketListener;
import com.example.as1.ExternalControllers.WebSocketManager;
import com.example.as1.R;

import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONException;

public class FriendsChatPage extends AppCompatActivity implements WebSocketListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friends_chat_page);

        //URL from springboot endpoint, DOES IT NEED TO BE WS OR HTTP??
        //what is {target}: /chat/{username}/{target} target is message reciever
        //message reciever should be groupname or user id
         String BASE_URL = "ws://coms-309-051.class.las.iastate.edu:8080/chat/";
         EditText usernameEtx;
         TextView msgTv;

        Button connectBtn = findViewById(R.id.bt1);
        Button connect2Btn = findViewById(R.id.group2_btn);
        Button sendBtn =  findViewById(R.id.bt2);
        EditText msgEtx =  findViewById(R.id.et2);
        TextView welcome = findViewById(R.id.welcome_display);

        //TODO: get call /chat/messages/target for messages history

        //Get global user data for get request (just need id for get req)
        User getGlobal = User.getInstance();
        //Get req for user data, need to be sure global user has id set after logging in
        //TODO: make get and post reqs generic then call this from generic
        getGlobal = getUserData(this.getApplicationContext(),getGlobal);

        welcome.setText("Welcome " + getGlobal.getName() + "!");


        /* connect button listener */
        User finalGetGlobal = getGlobal;
        connectBtn.setOnClickListener(view -> {
            //set server url with url + username (may need changed)
            String serverUrl = BASE_URL + finalGetGlobal.getName().toString() + "/StockGroup1";

            // Establish WebSocket connection and set listener
            WebSocketManager.getInstance().connectWebSocket(serverUrl);
            WebSocketManager.getInstance().setWebSocketListener(FriendsChatPage.this);
        });

        connect2Btn.setOnClickListener(view -> {
            //set server url with url + username (may need changed)
            String serverUrl = BASE_URL + finalGetGlobal.getName().toString() + "/StockGroup2";

            // Establish WebSocket connection and set listener
            WebSocketManager.getInstance().connectWebSocket(serverUrl);
            WebSocketManager.getInstance().setWebSocketListener(FriendsChatPage.this);
        });

        /* send button listener */
        sendBtn.setOnClickListener(v -> {
            try {
                // send message
                WebSocketManager.getInstance().sendMessage(msgEtx.getText().toString());
            } catch (Exception e) {
                Log.i("ExceptionSendMessage:", e.getMessage().toString());
            }
        });


    }

    @Override
    public void onWebSocketMessage(String message) {
        /**
         * In Android, all UI-related operations must be performed on the main UI thread
         * to ensure smooth and responsive user interfaces. The 'runOnUiThread' method
         * is used to post a runnable to the UI thread's message queue, allowing UI updates
         * to occur safely from a background or non-UI thread.
         */
        TextView msgTv =  findViewById(R.id.tx1);
        runOnUiThread(() -> {
            String s = msgTv.getText().toString();
            msgTv.setText(s + "\n"+message);
        });
    }

    @Override
    public void onWebSocketClose(int code, String reason, boolean remote) {
        String closedBy = remote ? "server" : "local";
        TextView msgTv =  findViewById(R.id.tx1);
        runOnUiThread(() -> {
            String s = msgTv.getText().toString();
            msgTv.setText(s + "---\nconnection closed by " + closedBy + "\nreason: " + reason);
        });
    }

    @Override
    public void onWebSocketOpen(ServerHandshake handshakedata) {}

    @Override
    public void onWebSocketError(Exception ex) {}

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
}
