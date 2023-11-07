package com.example.as1.screens;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.example.as1.ExternalControllers.WebSocketListener;
import com.example.as1.ExternalControllers.WebSocketManager;
import com.example.as1.R;

import org.java_websocket.handshake.ServerHandshake;

public class GroupPage extends AppCompatActivity implements WebSocketListener {

    private String BASE_URL = "ws://coms-309-051.class.las.iastate.edu:8080/chat/";
    private Button connectBtn, sendBtn;
    private EditText msgEtx;
    public View groupView;
    private TextView msgTv;
    private TextView usernameEtx;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.group_page);

        connectBtn = findViewById(R.id.connectButton);
        connectBtn.setOnClickListener(view -> {
            //TODO : change to global user
            String serverUrl = BASE_URL + usernameEtx.getText().toString() + "/StockGroup1";

            WebSocketManager.getInstance().connectWebSocket(serverUrl);
            WebSocketManager.getInstance().setWebSocketListener(GroupPage.this);
        });


        /* send button listener */
        sendBtn = findViewById(R.id.sendButton);
        sendBtn.setOnClickListener(v -> {
            try {
                msgEtx = findViewById(R.id.messageEditText);
                WebSocketManager.getInstance().sendMessage(msgEtx.getText().toString());
            } catch (Exception e) {
                Log.d("ExceptionSendMessage:", e.getMessage().toString());
            }
        });

    }

    //responding to messages from the server
    //important
    @Override
    public void onWebSocketMessage(String message) {
        /**
         * In Android, all UI-related operations must be performed on the main UI thread
         * to ensure smooth and responsive user interfaces. The 'runOnUiThread' method
         * is used to post a runnable to the UI thread's message queue, allowing UI updates
         * to occur safely from a background or non-UI thread.
         */
        msgTv  = findViewById(R.id.chatTextView);

        runOnUiThread(() -> {
            String s = msgTv.getText().toString();
            msgTv.setText(s + "\n" + message);
        });
    }

    @Override
    public void onWebSocketClose(int code, String reason, boolean remote) {
        msgTv  = findViewById(R.id.chatTextView);
        String closedBy = remote ? "server" : "local";
        runOnUiThread(() -> {
            String s = msgTv.getText().toString();
            msgTv.setText(s + "---\nconnection closed by " + closedBy + "\nreason: " + reason);
        });
    }

    @Override
    public void onWebSocketOpen(ServerHandshake handshakedata) {
        Log.i("Websocket", "Opened");
    }

    @Override
    public void onWebSocketError(Exception ex) {
        Log.i("Websocket", "Error" + ex.getMessage());
    }


}//end