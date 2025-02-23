package com.example.navviewfe.Screens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.example.navviewfe.R;


import android.content.Context;
import android.util.Log;




import com.google.android.material.navigation.NavigationView;

public class LoggedInPage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;

//    Toolbar toolbar;

    Menu menu;

    TextView textView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        setContentView(R.layout.logged_in_page);


        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        textView = findViewById(R.id.textView);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        toolbar.setSubtitle("");
        setSupportActionBar(toolbar);

        menu = navigationView.getMenu();
//        menu.findItem(R.id.nav_logout).setVisible(false);
        menu.findItem(R.id.nav_login).setVisible(false);



        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(R.id.nav_home);


        RelativeLayout stockListLayout = findViewById(R.id.stockListRL);
        RelativeLayout stocksLayout = findViewById(R.id.stocksRL);
        RelativeLayout profileLayout = findViewById(R.id.profileRL);
        RelativeLayout groupsLayout = findViewById(R.id.groupsRL);


//        RelativeLayout tutorialLayout = findViewById(R.id.tutorialRL);
//        RelativeLayout logoutLayout = findViewById(R.id.logoutRL);

        //temp1RL
        //temp2RL
        RelativeLayout portfolioLayout = findViewById(R.id.tutorialRL);
        RelativeLayout newsLayout = findViewById(R.id.logoutRL);

        RelativeLayout tutorialLayout = findViewById(R.id.temp1RL);
        RelativeLayout logoutLayout = findViewById(R.id.temp2RL);



//        RelativeLayout tempLoginLayout = findViewById(R.id.tempLogin);



        stockListLayout.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
//                setContentView(R.layout.activity_profile);
//                setContentView(R.layout.stock_list_page);
                Intent intent = new Intent(LoggedInPage.this, StockList.class);
                startActivity(intent);
            }
        });

        stocksLayout.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(LoggedInPage.this, StockPage.class);
                startActivity(intent);
            }
        });

        profileLayout.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(LoggedInPage.this, ProfilePage.class);
                startActivity(intent);
            }
        });

        groupsLayout.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(LoggedInPage.this, GroupPage.class);
                startActivity(intent);
            }
        });

        tutorialLayout.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(LoggedInPage.this, TutorialsPage.class);
                startActivity(intent);
            }
        });

        logoutLayout.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(LoggedInPage.this, StartPage.class);
                startActivity(intent);
//                setContentView(R.layout.login_page);
            }
        });



        portfolioLayout.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(LoggedInPage.this, PortfolioPage.class);
                startActivity(intent);
            }
        });

        newsLayout.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(LoggedInPage.this, NewsPage.class);
                startActivity(intent);
            }
        });



//        tempLoginLayout.setOnClickListener(new View.OnClickListener(){
//            public void onClick(View v) {
////                setContentView(R.layout.login_page);
//
//                Intent intent = new Intent(LoggedInPage.this, StartPage.class);
//                startActivity(intent);
//            }
//        });


    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.nav_home){

        } else if (menuItem.getItemId() == R.id.nav_profile){
            Intent intent = new Intent(LoggedInPage.this, ProfilePage.class);
            startActivity(intent);
        } else if (menuItem.getItemId() == R.id.nav_group) {
            Intent intent = new Intent(LoggedInPage.this, GroupPage.class);
            startActivity(intent);
        } else if (menuItem.getItemId() == R.id.nav_group_edit) {
            Intent intent = new Intent(LoggedInPage.this, AdminDashboardPage.class);
            startActivity(intent);
        } else if (menuItem.getItemId() == R.id.nav_stock) {
            Intent intent = new Intent(LoggedInPage.this, StockPage.class);
            startActivity(intent);
        } else if (menuItem.getItemId() == R.id.nav_stock_list) {
            Intent intent = new Intent(LoggedInPage.this, StockList.class);
            startActivity(intent);
        } else if (menuItem.getItemId() == R.id.nav_profile) {
            Intent intent = new Intent(LoggedInPage.this, ProfilePage.class);
            startActivity(intent);
        } else if (menuItem.getItemId() == R.id.nav_logout) {
            Intent intent = new Intent(LoggedInPage.this, MainActivity.class);
            startActivity(intent);
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;

    }




}