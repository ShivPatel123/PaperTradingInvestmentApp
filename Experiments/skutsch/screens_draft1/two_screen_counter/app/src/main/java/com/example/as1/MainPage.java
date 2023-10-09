package com.example.as1;


import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainPage extends AppCompatActivity {
        /* need profile page
         * tutorials page
         * groups page
         * portfolio page
         * button to stock preview page (with tutorials?)
         * stock listing
         */
    Button toStockPreview_btn;
    Button toProfile_btn;
    Button toTutorials_btn;
    Button toGroup_btn;
    Button toPortfolio_btn;
    Button toHelp_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);

        //button to Profile page
        toProfile_btn = findViewById(R.id.profile_Main_btn);
        toProfile_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainPage.this, ProfilePage.class);
                startActivity(intent);
            }
        });

        //button to tutorials page
        toTutorials_btn = findViewById(R.id.tutorial_Main_btn);
        toTutorials_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainPage.this, TutorialsPage.class);
                startActivity(intent);
            }
        });

        //button to group page
        toGroup_btn = findViewById(R.id.group_Main_btn);
        toGroup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainPage.this, GroupPage.class);
                startActivity(intent);
            }
        });

        //button to stock preview page
        toStockPreview_btn = findViewById(R.id.stockPreview_Main_btn);
        toStockPreview_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainPage.this, StockPage.class);
                startActivity(intent);
            }
        });

        //button to login page
        toPortfolio_btn = findViewById(R.id.portfolio_Main_btn);
        toPortfolio_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainPage.this, PortfolioPage.class);
                startActivity(intent);
            }
        });

        //button to login page
        toHelp_btn = findViewById(R.id.help_Main_btn);
        toHelp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainPage.this, HelpPage.class);
                startActivity(intent);
            }
        });



    }

    }

