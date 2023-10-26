package com.example.as1.screens;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.as1.R;

public class CreateAccountPage extends AppCompatActivity {

    Button toHome_btn;
    Button toLogin_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account);


        //button back to home page
        toHome_btn = findViewById(R.id.toHome_loginPagebtn);
        toHome_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(CreateAccountPage.this, HomePage.class);
                startActivity(intent);
            }
        });

        //button to Create Account page
        toLogin_btn = findViewById(R.id.toLogin_frmSignUp_Btn);
        toLogin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(CreateAccountPage.this, LoginPage.class);
                startActivity(intent);
            }
        });


    }
}