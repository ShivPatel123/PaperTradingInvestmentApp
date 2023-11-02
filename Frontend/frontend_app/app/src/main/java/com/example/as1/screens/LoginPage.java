package com.example.as1.screens;

import static com.example.as1.Controllers.User.getInstance;
import static com.example.as1.Controllers.UserSingleton.getGlobalUser;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;

import com.android.volley.toolbox.Volley;
import com.example.as1.Controllers.LoginAttempt;
import com.example.as1.Controllers.User;
import com.example.as1.R;
import com.example.as1.ExternalControllers.VolleySingleton;

import android.util.Log;
import android.widget.TextView;

import org.json.*;

public class LoginPage extends AppCompatActivity {

    //TODO:make javadoc for frontend layout files and all files

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        //Initialize stuff
        Button toHome_btn = findViewById(R.id.toHome_loginPagebtn);
        Button toSignUp_btn = findViewById(R.id.toSignupBtn);
        Button sendLoginReq_btn = findViewById(R.id.sendLoginReq_btn);


        EditText usernameInput_txt = findViewById(R.id.usernameLogin_txtInput);
        EditText passwordInput_txt = findViewById(R.id.passwordLogin_txtInput);
        EditText volleyOutput_txt = findViewById(R.id.loginReqResponse_txt);

        //button back to home page
        toHome_btn.setOnClickListener(view -> {
            Intent intent = new Intent(LoginPage.this, HomePage.class);
            startActivity(intent);
        });

        //button to Create Account page
        toSignUp_btn.setOnClickListener(view -> {
            Intent intent = new Intent(LoginPage.this, CreateAccountPage.class);
            startActivity(intent);
        });

        //button to try login
        sendLoginReq_btn.setOnClickListener(view -> {
            //parse inputs
            String usernameInput = usernameInput_txt.getText().toString();
            String passwordInput = passwordInput_txt.getText().toString();
            //make new login
           LoginAttempt loginAuth = new LoginAttempt (usernameInput, passwordInput);
            //Post login
           makeLoginPostReq(this.getApplicationContext(), loginAuth);
            Log.i("after post req called", "");

           //TODO: make sure this is working: If backend returns success, open main page
           if(volleyOutput_txt.getText().toString() == "{\"message\":\"success\"}") {
               //set global user variables for username and passw
               //TODO: if the login is successful, get the user data and set the global user to it. this
               // should solve the functions returning nothing.
               getInstance().setUsername(usernameInput);
               getInstance().setPassword(passwordInput);
               //go to MainPage
               Intent intent = new Intent(LoginPage.this, MainPage.class);
               startActivity(intent);
           }
        });
    }

    public void makeLoginPostReq(Context context, LoginAttempt loginA) {
        String URL_JSON_OBJECT = "http://10.90.75.130:8080/login";
        EditText volleyOutput_txt = findViewById(R.id.loginReqResponse_txt);
        JSONObject objectBody = new JSONObject();

        // Convert input to JSONObject
        try {
            objectBody.put("username",loginA.getUsername());
            objectBody.put("password",loginA.getPassword());
            Log.i("loginA JSON Object: ", objectBody.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Create new request
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                URL_JSON_OBJECT,
                objectBody,
                response -> {
                    volleyOutput_txt.setText(response.toString());
                    Log.i("Post req response", "makeLoginPostReq: " + response);
                },
                error -> Log.i("Login error", "error message: " + error)) { };

        // Adding request to request queue
        VolleySingleton.getInstance(context.getApplicationContext()).addToRequestQueue(request);

    }

    public User getUserData(Context context, User user) {
        String URL_JSON_OBJECT = "http://10.90.75.130:8080/user/".concat(String.valueOf(user.getId())); //TODO: no user id after login

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
                        String numStocks = response.getString("numStocks");
                        String username = response.getString("username");
                        String password = response.getString("password");

                        // Populate text views with the parsed data
                        user.setName(name);
                        user.setEmail(email);
                        user.setId(Integer.parseInt(id));
                        user.setDob(dob);
                        user.setMoney(Double.parseDouble(money));
                        // user.setNumStocks(Integer.parseInt(numStocks));
                        user.setUsername(username);
                        user.setPassword(password);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> Log.e("Error Message for ", "getUserData: " + error.getMessage().toString())) {
        };

        // Adding request to request queue
        VolleySingleton.getInstance(context.getApplicationContext()).addToRequestQueue(request);
        return user;
    }

}