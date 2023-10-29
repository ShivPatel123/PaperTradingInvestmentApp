package com.example.as1.screens;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;

import com.example.as1.Controllers.LoginAttempt;
import com.example.as1.R;
import com.example.as1.VolleySingleton;

import android.util.Log;
import org.json.*;

public class LoginPage extends AppCompatActivity {
//set variable fro userID, use that throughout the app.
//setter methods in profile page
// stock prediction page/ feature??
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

           //If backend returns success, open main page
           if(volleyOutput_txt.getText().toString() == "{\"message\":\"success\"}") {
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
            Log.d("loginA JSON Object: ", objectBody.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Create new request
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                URL_JSON_OBJECT,
                objectBody,
                response -> volleyOutput_txt.setText(response.toString()),
                error -> volleyOutput_txt.setText(error.getMessage())) { };

        // Adding request to request queue
        VolleySingleton.getInstance(context.getApplicationContext()).addToRequestQueue(request);
    }

}