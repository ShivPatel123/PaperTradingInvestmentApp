package com.example.as1.screens;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.as1.R;

public class NavPage extends AppCompatActivity {
    Button toStock_btn;
    Button toProfile_btn;
    Button toTutorials_btn;
    Button toGroup_btn;
    Button toPortfolio_btn;
    Button toFriends_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_page);

        //button to Profile page
        toProfile_btn = findViewById(R.id.profile_Main_btn);
        toProfile_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(NavPage.this, ProfilePage.class);
                startActivity(intent);
            }
        });

        //button to tutorials page
        toTutorials_btn = findViewById(R.id.tutorial_Main_btn);
        toTutorials_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(NavPage.this, TutorialsPage.class);
                startActivity(intent);
            }
        });

        //button to group page
        toGroup_btn = findViewById(R.id.group_Main_btn);
        toGroup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(NavPage.this, GroupPage.class);
                startActivity(intent);
            }
        });

        //button to stock list page
        toStock_btn = findViewById(R.id.stockPreview_Main_btn);
        toStock_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(NavPage.this, StockList.class);
                startActivity(intent);
            }
        });

        //button to portfolio page
        toPortfolio_btn = findViewById(R.id.portfolio_Main_btn);
        toPortfolio_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(NavPage.this, PortfolioPage.class);
                startActivity(intent);
            }
        });

        //button to friends page
        toFriends_btn = findViewById(R.id.friends_Main_btn);
        toFriends_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(NavPage.this, FriendGroupPage.class);
                startActivity(intent);
            }
        });

    }

    }

