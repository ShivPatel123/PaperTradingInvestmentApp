package com.example.as1.screens;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.as1.R;

public class StockPage extends AppCompatActivity {

    Button toHome_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stock_page);

        //button to home pagetoHome_btn = findViewById(R.id.toLoginBtn);
        toHome_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(StockPage.this, HomePage.class);
                startActivity(intent);
            }
        });

    }
}
