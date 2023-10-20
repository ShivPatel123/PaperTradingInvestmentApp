package com.example.as1.screens;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;

import com.example.as1.LoginAttempt;
import com.example.as1.R;
import com.example.as1.VolleySingleton;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginPage extends AppCompatActivity {
//set variable fro userID, use that throughout the app.
//setter methods in profile page
    //stock prediction page/ feature??
    Button toHome_btn;
    Button toSignUp_btn;
    Button sendLoginReq_btn;

    EditText usernameInput_txt = findViewById(R.id.usernameLogin_txtInput);
    EditText passwordInput_txt = findViewById(R.id.passwordLogin_txtInput);
    EditText volleyOutput_txt = findViewById(R.id.loginReqResponse_txt);
    String usernameInput;
    String passwordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        //button back to home page
        toHome_btn = findViewById(R.id.toHome_loginPagebtn);
        toHome_btn.setOnClickListener(view -> {
            Intent intent = new Intent(LoginPage.this, HomePage.class);
            startActivity(intent);
        });

        //button to Create Account page
        toSignUp_btn = findViewById(R.id.toSignupBtn);
        toSignUp_btn.setOnClickListener(view -> {
            Intent intent = new Intent(LoginPage.this, CreateAccountPage.class);
            startActivity(intent);
        });

        //button to try login
        sendLoginReq_btn = findViewById(R.id.sendLoginReq_btn);
        sendLoginReq_btn.setOnClickListener(view -> {
            //parse inputs
            usernameInput = usernameInput_txt.getText().toString();
            passwordInput = passwordInput_txt.getText().toString();
            //make new login
            LoginAttempt loginAuth = new LoginAttempt (usernameInput, passwordInput);
            //Post login
            makeLoginPostReq(getApplicationContext(), loginAuth);
        });

    }

    public void makeLoginPostReq(Context context, LoginAttempt loginA) {
        String URL_JSON_OBJECT = "http://10.90.75.130:8080/login";

        // Convert input to JSONObject
        JSONObject objectBody = new JSONObject();
        try {
            objectBody = new JSONObject(loginA.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Create new request
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                URL_JSON_OBJECT,
                objectBody,
                //response function to lambda
                response -> volleyOutput_txt.setText(response.toString()),
                error -> volleyOutput_txt.setText(error.getMessage())) { };
        // Adding request to request queue
        VolleySingleton.getInstance(context.getApplicationContext()).addToRequestQueue(request);
    }
}