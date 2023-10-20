package com.example.as1.screens;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.as1.R;
import com.example.as1.RequestController;

public class LoginPage extends AppCompatActivity {

    Button toHome_btn;
    Button toSignUp_btn;
    Button sendLoginReq_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);


        //button back to home page
        toHome_btn = findViewById(R.id.toHome_loginPagebtn);
        toHome_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(LoginPage.this, HomePage.class);
                startActivity(intent);
            }
        });

        //button to Create Account page
        toSignUp_btn = findViewById(R.id.toSignupBtn);
        toSignUp_btn.setOnClickListener(view -> {
            Intent intent = new Intent(LoginPage.this, CreateAccountPage.class);
            startActivity(intent);
        });

        //button to try login
        //TODO : parse input from username/password EditText windows, create new user object with parsed info, send request
        //TODO : login authetication on backend or frontend side?
        // Frontend will have to have if/else to determine what to do with backend response
        sendLoginReq_btn = findViewById(R.id.sendLoginReq_btn);
        sendLoginReq_btn.setOnClickListener(view -> {
        });

    }
}