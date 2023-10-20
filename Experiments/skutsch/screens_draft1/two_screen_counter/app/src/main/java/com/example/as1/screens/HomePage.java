package com.example.as1.screens;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.as1.R;

public class HomePage extends AppCompatActivity {

    Button toStocks_btn;
    Button toLogin_btn;
    Button toCreateAccount_btn;
    Button toHelp_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);

        //button to login page
        toLogin_btn = findViewById(R.id.toLoginBtn);
        toLogin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(HomePage.this, LoginPage.class);
                startActivity(intent);
            }
        });

        //button to stock page
        toStocks_btn = findViewById(R.id.toStockBtn);
        toStocks_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(HomePage.this, StockPage.class);
                startActivity(intent);
            }
        });

        //button to create account page
        toStocks_btn = findViewById(R.id.toCreateAccountBtn);
        toStocks_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(HomePage.this, CreateAccountPage.class);
                startActivity(intent);
            }
        });




    }


}