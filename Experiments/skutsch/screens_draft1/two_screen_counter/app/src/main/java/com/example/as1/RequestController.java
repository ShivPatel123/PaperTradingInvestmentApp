package com.example.as1;

import android.app.Activity;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;


public interface RequestController{

    //TODO : change functions to be acceptable defaults
    //TODO : change output text windows in functions to be params of function of this interface

    /**
     * GET JSON OBJECT
     */

   // private static final String URL_JSON_OBJECT = "http://10.90.75.130:8080/users";
    static void makeJsonObjReq(String URL_JSON_OBJECT, Context context){
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                Request.Method.GET,
                URL_JSON_OBJECT,
                null, // Pass null as the request body since it's a GET request

                //response function in lambda function form
                response -> {
                    Log.d("Volley Response", response.toString());
                    //print string before parsing for debugging
                   // JsonObjectDisplay.setText(response.toString());
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
//                        userName.setText(name);
//                        userEmail.setText(email);
//                        userID.setText(id);
//                        userDOB.setText(dob);
//                        userMoney.setText(money);
//                        userNumStocks.setText(numStocks);
//                        userUsername.setText(username);
//                        userPassword.setText(password);


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
                //headers.put("Content-Type", "application/json");
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
        VolleySingleton.getInstance(context.getApplicationContext()).addToRequestQueue(jsonObjReq);

    }


    /**
     *  POST JSON OBJECT
     */

    default void makeJSONPostRequest(String URL_JSON_OBJECT, Context context) {

        // Convert input to JSONObject
        JSONObject objectBody = new JSONObject();
        try {
//            User user1 = new User(5,20,"userTest", "userTest@gmail","07252002", "userTest","userPass");
//            objectBody = new JSONObject(user1.toString());
            // etRequest should contain a JSON object string as your POST body
            // similar to what you would have in POSTMAN-body field
            // and the fields should match with the object structure of @RequestBody on sb

        } catch (Exception e) {
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                URL_JSON_OBJECT,
                objectBody,
                //response function to lambda
                response -> JsonObjectDisplay.setText(response.toString()),
                error -> JsonObjectDisplay.setText(error.getMessage()))
        {
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
        VolleySingleton.getInstance(context.getApplicationContext()).addToRequestQueue(request);
    }

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
                    msgResponse.setText("Volley Error: " + error.toString());
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

    private void makeStringPost() {

        StringRequest stringPost = new StringRequest(
                Request.Method.POST, //connect to endpoint
                URL_STRING_REQ,
                response -> {
                    // Handle the successful response here
                    Log.d("Volley Response", response);
                    inputStringText.getText().toString();
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

            @Override
            public byte[] getBody() {
                // Convert your string data to bytes (UTF-8 encoded) here
                String body = inputStringText.getText().toString();
                try {
                    return body.getBytes("UTF-8");
                } catch (UnsupportedEncodingException e) {
                    return null;
                }
            }
            @Override
            public String getBodyContentType() {
                return "string"; // Set the appropriate content type here
            }

        };

        // Adding request to request queue
        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringPost);
        // queue.add(stringRequest);
    }
    /**
     * GET JSON ARRAY
     */

    // Initialize the adapter with an empty list (data will be added later)
    private ListAdapter adapter;
    private static final String URL_JSON_ARRAY = "https://jsonplaceholder.typicode.com/users";

//        adapter = new ListAdapter(this, new ArrayList<>());
//        listView.setAdapter(adapter);
//
//        btnJsonArrReq.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            makeJsonArrayReq();
//        }

    private void makeJsonArrayReq() {
        JsonArrayRequest jsonArrReq = new JsonArrayRequest(
                Request.Method.GET,
                URL_JSON_ARRAY,
                null, // Pass null as the request body since it's a GET request
                response -> {
                    Log.d("Volley Response", response.toString());

                    // Parse the JSON array and add data to the adapter
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            String name = jsonObject.getString("name");
                            String email = jsonObject.getString("email");

                            // Create a ListItemObject and add it to the adapter
                            ListItemObject item = new ListItemObject(name, email);
                            adapter.add(item);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                error -> Log.e("Volley Error", error.toString())) {
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
//                params.put("param1", "value1");
//                params.put("param2", "value2");
                return params;
            }
        };

        // Adding request to request queue
        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonArrReq);
    }

    void makeJSONPostRequest("http://10.90.75.130:8080/login",Context context);
}//end

