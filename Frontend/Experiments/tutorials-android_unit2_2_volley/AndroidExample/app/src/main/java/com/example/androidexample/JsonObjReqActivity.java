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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class JsonObjReqActivity extends AppCompatActivity {

    private Button btnJsonObjReq;
    private Button btnJsonObjPost;
    private TextView userName, userEmail, userID, userMoney, userNumStocks, userDOB, userUsername, userPassword, JsonObjectInput, JsonObjectDisplay;
//    private String TAG = JsonObjReqActivity.class.getSimpleName();
//    private String tag_json_obj = "jobj_req";

    private static final String URL_JSON_OBJECT = "http://10.90.75.130:8080/users";

// @/users returns JSON object ARRAY, JSON post sends JSON object
    //either get 1 user or change to get JSON array, post needs to be JSON object type
    //hard code a user, try to send
    // need different methods on backend for getting string vs getting object??
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_obj_req);
      //  RequestQueue queue = Volley.newRequestQueue(this);

        //User user1 = new User(5,20,"userTest", "userTest@gmail","07252002", "userTest","userPass");

        btnJsonObjReq = findViewById(R.id.btnJsonObj);
        btnJsonObjPost = findViewById(R.id.btnJsonObj_post);
        userName = findViewById(R.id.userName);
        userEmail = findViewById(R.id.emailTv);
        userID = findViewById(R.id.userID);
        userMoney = findViewById(R.id.userMoney);
        userNumStocks = findViewById(R.id.userNumStocks);
        userDOB = findViewById(R.id.userDOB);
        userUsername = findViewById(R.id.userUsername);
        userPassword = findViewById(R.id.userPassword);

        JsonObjectInput = findViewById(R.id.getJsonObject);
        JsonObjectDisplay = findViewById(R.id.objectDisplay);

        btnJsonObjReq.setOnClickListener(v -> makeJsonObjReq());
        btnJsonObjPost.setOnClickListener(v -> makeJSONPostRequest());
   }

    /**
     * Making json object request
     */
    private void makeJsonObjReq() {
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                Request.Method.GET,
                URL_JSON_OBJECT,
                null, // Pass null as the request body since it's a GET request

                //response function in lambda function form
                response -> {
                    Log.d("Volley Response", response.toString());
                    //print string before parsing for debugging
                    JsonObjectDisplay.setText(response.toString());
                    try {
                        // Parse JSON object data
                        String name = response.getString("name");
                        String email = response.getString("email");
                        String id = response.getString("id");
                        String dob = response.getString("dob");
                        String money = response.getString("money");
                        String numStocks = response.getString("numStocks");
                        String username = response.getString("username");
                        String password = response.getString("password");


                        // Populate text views with the parsed data
                        userName.setText(name);
                        userEmail.setText(email);
                        userID.setText(id);
                        userDOB.setText(dob);
                        userMoney.setText(money);
                        userNumStocks.setText(numStocks);
                        userUsername.setText(username);
                        userPassword.setText(password);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> Log.e("Volley Error", error.toString())
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                //headers.put("Authorization", "Bearer YOUR_ACCESS_TOKEN");
//                headers.put("Content-Type", "application/json");
                return headers;
            }

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
//                params.put("name", "test");
//                params.put("param2", "value2");
                return params;
            }
        };

        // Adding request to request queue
        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonObjReq);
    }

    private void makeJSONPostRequest() {

        // Convert input to JSONObject
        JSONObject objectBody = new JSONObject();
        try {
            User user1 = new User(5,20,"userTest", "userTest@gmail","07252002", "userTest","userPass");
            // etRequest should contain a JSON object string as your POST body
            // similar to what you would have in POSTMAN-body field
            // and the fields should match with the object structure of @RequestBody on sb
            objectBody = new JSONObject(user1.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                URL_JSON_OBJECT,
                objectBody,
                //response function to lambda
                response -> JsonObjectDisplay.setText(response.toString()),
                error -> JsonObjectDisplay.setText(error.getMessage())
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
        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(request);
    }


}
// [{"id":1,"money":1987654,"name":"Nick","email":"kokottni@iastate.edu","dob":"oldenough","username":"user","password":"Password"}