package com.example.as1.screens;

import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.as1.Controllers.User;
import com.example.as1.ExternalControllers.WebSocketListener;
import com.example.as1.ExternalControllers.WebSocketManager;
import com.example.as1.R;

import org.java_websocket.handshake.ServerHandshake;

public class FriendsPage extends AppCompatActivity implements WebSocketListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friends_page);

        //URL from springboot endpoint, DOES IT NEED TO BE WS OR HTTP??
        //what is {target}: /chat/{username}/{target} target is message reciever
        //message reciever should be groupname or user id
         String BASE_URL = "ws://coms-309-051.class.las.iastate.edu:8080/chat/";
         EditText usernameEtx;
         TextView msgTv;

        Button connectBtn = findViewById(R.id.bt1);
        Button sendBtn =  findViewById(R.id.bt2);
        EditText msgEtx =  findViewById(R.id.et2);

        //Get global user data for get request (just need id for get req)
        User getGlobal = User.getInstance();
        //Get req for user data, need to be sure global user has id set after logging in
        //TODO: make get and post reqs generic then call this from generic
       // getGlobal = User.getUserData(this.getApplicationContext(),getGlobal);


        /* connect button listener */
        connectBtn.setOnClickListener(view -> {
            //set server url with url + username (may need changed)
            String serverUrl = BASE_URL + getGlobal.getUsername().toString();

            // Establish WebSocket connection and set listener
            WebSocketManager.getInstance().connectWebSocket(serverUrl);
            WebSocketManager.getInstance().setWebSocketListener(FriendsPage.this);
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


}
