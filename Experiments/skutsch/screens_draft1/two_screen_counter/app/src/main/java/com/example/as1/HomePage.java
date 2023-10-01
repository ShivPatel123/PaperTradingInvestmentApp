package com.example.as1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomePage extends AppCompatActivity {

    Button toStocks_btn;
    Button toLogin_btn;
    Button toCreateAccount_btn;
    Button toHelp_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);

        //button to stock page
        toStocks_btn = findViewById(R.id.toCounterBtn);
        toStocks_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(HomePage.this, CounterActivity.class);
                startActivity(intent);
            }
        });

        //button to login page
        toLogin_btn = findViewById(R.id.toLoginBtn);
        toLogin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(HomePage.this, CounterActivity.class);
                startActivity(intent);
            }
        });


    }


}