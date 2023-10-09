package com.example.androidexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class StringReqActivity extends AppCompatActivity {

    private Button btnStringReq;
    private TextView msgResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_string_req);

        btnStringReq = (Button) findViewById(R.id.btnStringReq);
        msgResponse = (TextView) findViewById(R.id.msgResponse);

        btnStringReq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeStringReq();
            }
        });
    }

    //  private static final String URL_STRING_REQ = "https://jsonplaceholder.typicode.com/users/1";
    //public static final String URL_STRING_REQ = "https://b12f7c8b-6dac-45d7-ae37-b4741526b57e.mock.pstmn.io/users/1";
    public static final String URL_STRING_REQ = "http://10.90.75.130:8080/users";
    /**
     * Making string request
     **/
    private void makeStringReq() {

        StringRequest stringRequest = new StringRequest(
                Request.Method.GET, //connect to endpoint
                URL_STRING_REQ,
                response -> {
                    // Handle the successful response here
                    Log.d("Volley Response", response);
                    msgResponse.setText(response.toString());
                },
                error -> {
                    // Handle any errors that occur during the request
                    Log.e("Volley Error", error.toString());
                    msgResponse.setText("Volley Error");
                }
        ) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
//                headers.put("Authorization", "Bearer YOUR_ACCESS_TOKEN");
//                headers.put("Content-Type", "application/json");
                return headers;
            }

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
               // params.put("money", "0");
                return params;
            }

        };

        // Adding request to request queue
        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
       // queue.add(stringRequest);
    }

}