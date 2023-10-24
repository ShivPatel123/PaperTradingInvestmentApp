package com.example.as1.screens;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.as1.R;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);

        //Instantiate buttons
        Button toStocks_btn = findViewById(R.id.toStockBtn);
        Button toLogin_btn = findViewById(R.id.toLoginBtn);
        Button toCreateAccount_btn = findViewById(R.id.toCreateAccountBtn);

        //button to login page
        toLogin_btn.setOnClickListener(v -> {
            Intent intent = new Intent(HomePage.this, LoginPage.class);
            startActivity(intent);
        });

        //button to stock page
        toStocks_btn.setOnClickListener(v -> {
            Intent intent = new Intent(HomePage.this, StockPage.class);
            startActivity(intent);
        });

        //button to create account page
        toCreateAccount_btn.setOnClickListener(v -> {
            Intent intent = new Intent(HomePage.this, CreateAccountPage.class);
            startActivity(intent);
        });




    }


}