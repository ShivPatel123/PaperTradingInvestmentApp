package com.example.as1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StockPage extends AppCompatActivity {

    Button toStocks_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stock_page);

        //button to login page
  /*      toLogin_btn = findViewById(R.id.toLoginBtn);
        toLogin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(HomePage.this, LoginPage.class);
                startActivity(intent);
            }
        });
   */
    }
}
