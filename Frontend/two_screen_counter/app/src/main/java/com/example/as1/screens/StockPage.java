package com.example.as1.screens;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.as1.R;

public class StockPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stock_page);

        //Buttons
        Button toHome_btn = findViewById(R.id.toHome_btn);

        //button to home page
        toHome_btn.setOnClickListener(v -> {
            Intent intent = new Intent(StockPage.this, HomePage.class);
            startActivity(intent);
        });

    }
}
