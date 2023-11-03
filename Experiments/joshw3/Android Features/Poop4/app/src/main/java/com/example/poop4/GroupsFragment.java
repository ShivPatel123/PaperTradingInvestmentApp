package com.example.poop4;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.poop4.WebSocketListener;
import com.example.poop4.WebSocketManager;



import org.java_websocket.handshake.ServerHandshake;
import org.w3c.dom.Text;





/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GroupsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */


//Implements WebSocketListener

public class GroupsFragment extends Fragment implements View.OnClickListener, WebSocketListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public String userName;



    /* Replace w/ server URL */
//    private String BASE_URL = "ws://10.0.2.2:8080/chat/";

    private String BASE_URL = "ws://coms-309-051.class.las.iastate.edu:8080/chat/";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Button connectBtn, sendBtn;

    private EditText msgEtx;

    private TextView msgTv;

    public View groupView;

    private TextView usernameEtx;


    public GroupsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GroupsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GroupsFragment newInstance(String param1, String param2) {
        GroupsFragment fragment = new GroupsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }




    }


    @Override
    public void onClick(View v) {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        groupView = inflater.inflate(R.layout.fragment_groups, container, false);

        connectBtn = (Button) groupView.findViewById(R.id.connectButton);
        sendBtn = (Button) groupView.findViewById(R.id.sendButton);
        usernameEtx = (TextView) groupView.findViewById(R.id.usernameTextView);
        msgEtx = (EditText) groupView.findViewById(R.id.messageEditText);
        msgTv = (TextView) groupView.findViewById(R.id.chatTextView);



        //  /StockGroup2

        connectBtn.setOnClickListener(view -> {
            String serverUrl = BASE_URL + usernameEtx.getText().toString() + "/StockGroup1";
            Log.d(serverUrl, "sadge");

            //Connect to server by calling this method
            // Establish WebSocket connection and set listener
            WebSocketManager.getInstance().connectWebSocket(serverUrl);

            //was (MainActivity.this)   //GroupsFragment.this
            WebSocketManager.getInstance().setWebSocketListener(GroupsFragment.this);

        });


        /* send button listener */
        sendBtn.setOnClickListener(v -> {
            try {

                // send message
                //get the websocket + send message
                WebSocketManager.getInstance().sendMessage(msgEtx.getText().toString());
            } catch (Exception e) {
                Log.d("ExceptionSendMessage:", e.getMessage().toString());
            }
        });


//        return inflater.inflate(R.layout.fragment_groups, container, false);
        return groupView;
    }




    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.connectButton).setOnClickListener(this);

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
        getActivity().runOnUiThread(() -> {
            String s = msgTv.getText().toString();
            msgTv.setText(s + "\n"+message);
        });
    }

    @Override
    public void onWebSocketClose(int code, String reason, boolean remote) {
        String closedBy = remote ? "server" : "local";
        getActivity().runOnUiThread(() -> {
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




}