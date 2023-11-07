package com.example.as1.screens;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.as1.ExternalControllers.VolleySingleton;
import com.example.as1.ExternalControllers.WebSocketListener;
import com.example.as1.Fragments.GroupsFragment;
import com.example.as1.Fragments.HomeFragment;
import com.example.as1.Fragments.ProfileFragment;
import com.example.as1.R;

import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class StockPage extends AppCompatActivity implements WebSocketListener {

    private ActivityMainBinding binding;
    private Button connectBtn, sendBtn;
    private EditText msgEtx;
    private TextView msgTv;
    private TextView stockName;
    private Button jsonObjBtn;
    private static final String URL_JSON_OBJECT = "https://jsonplaceholder.typicode.com/users/1";

    //private static final String URL_JSON_OBJECT = "http://coms-309-051.class.las.iastate.edu:8080/stock/";
    //For Websockets (Replace w/ server later)
    private String BASE_URL = "ws://10.0.2.2:8080/chat/";
    private TextView testName;
    private TextView testPhone;
    private TextView testLow;
    private TextView testHigh;
    private ListView stockListView;
    public String testStock[] = {"Apple", "Banana", "Orange", "Watermelon"};
//    public ArrayList<String> mainStockList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());


        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            if (item.getItemId() == R.id.home){
                replaceFragment(new HomeFragment());
//                setContentView(R.layout.activity_main);
            } else if (item.getItemId() == R.id.groups){
                replaceFragment(new GroupsFragment());
            } else if (item.getItemId() == R.id.profile) {
                replaceFragment(new ProfileFragment());

            }

            return true;
        });


        /* initialize UI elements for Group/Websockets */
        connectBtn = findViewById(R.id.connectButton);
        sendBtn = findViewById(R.id.sendButton);
        msgEtx = findViewById(R.id.messageEditText);
        msgTv = findViewById(R.id.chatTextView);

        /* initialize UI elements for Home */
        Button add_remove = findViewById(R.id.add_removeButton);
        stockName = findViewById(R.id.stockNameTextView);
        testName = findViewById(R.id.testEmail);
        testPhone = findViewById(R.id.testPrice);

        testLow = findViewById(R.id.stockLow);
        testHigh = findViewById(R.id.stockHigh);

        String test = "Test Stock";

//        /* initialize UI elements for portfolio */
        stockListView = (ListView) findViewById(R.id.stockListView);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.activity_list_view, R.id.test123, testStock);
        stockListView.setAdapter(arrayAdapter);

        add_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //stockName.setText(test);
                makeJsonObjReq();
            }
        });

    }


    public void onWebSocketMessage(String message) {
        /**
         * In Android, all UI-related operations must be performed on the main UI thread
         * to ensure smooth and responsive user interfaces. The 'runOnUiThread' method
         * is used to post a runnable to the UI thread's message queue, allowing UI updates
         * to occur safely from a background or non-UI thread.
         */
        runOnUiThread(() -> {
            String s = msgTv.getText().toString();
            msgTv.setText(s + "\n"+message);
        });
    }


    @Override
    public void onWebSocketClose(int code, String reason, boolean remote) {
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


    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();
    }
    private void makeJsonObjReq() {
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                Request.Method.GET,
                URL_JSON_OBJECT,
                null, // Pass null as the request body since it's a GET request
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Volley Response", response.toString());
                        stockName.setText(response.toString());

                        try {
                            // Parse JSON object data
                            String name = response.getString("name");
                            String email = response.getString("email");
                            String phone = response.getString("phone");

                            // Populate text views with the parsed data
                            stockName.setText(name);
                            testName.setText("200");
                            testPhone.setText("195");

                            testLow.setText(phone);
                            testHigh.setText(name);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Volley Error", error.toString());
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
//                headers.put("Authorization", "Bearer YOUR_ACCESS_TOKEN");
//                headers.put("Content-Type", "application/json");
                return headers;
            }

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
//                params.put("param1", "value1");
//                params.put("param2", "value2");
                return params;
            }
        };

        // Adding request to request queue
        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonObjReq);
    }







}






