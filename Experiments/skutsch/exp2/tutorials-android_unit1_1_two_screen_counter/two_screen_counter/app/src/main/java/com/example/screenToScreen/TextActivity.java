package com.example.screenToScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.as1.R;

import org.w3c.dom.Text;

public class TextActivity extends AppCompatActivity {

    Button screen1Btn;
    Button screen2Btn;
    Button screen3Btn;
    //TextView numberTxt;

    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);

        screen1Btn = findViewById(R.id.screen1Btn);
        screen2Btn = findViewById(R.id.screen2Btn);
        screen3Btn = findViewById(R.id.screen3Btn);
        //numberTxt = findViewById(R.id.number);

        screen1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(TextActivity.this, Screen1.class);
                startActivity(intent);
            }
        });

        screen2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(TextActivity.this, Screen2.class);
                startActivity(intent);
            }
        });

        screen3Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(TextActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }
}