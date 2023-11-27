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

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    Menu menu;

    TextView textView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        textView = findViewById(R.id.textView);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        menu = navigationView.getMenu();
        menu.findItem(R.id.nav_logout).setVisible(false);
        menu.findItem(R.id.nav_profile).setVisible(false);
        menu.findItem(R.id.nav_group).setVisible(false);
        menu.findItem(R.id.nav_group_edit).setVisible(false);



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
        RelativeLayout tutorialLayout = findViewById(R.id.tutorialRL);
        RelativeLayout logoutLayout = findViewById(R.id.logoutRL);

        RelativeLayout tempLoginLayout = findViewById(R.id.tempLogin);



        stockListLayout.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
//                setContentView(R.layout.activity_profile);
                setContentView(R.layout.stock_list_page);
            }
        });

        stocksLayout.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {

            }
        });

        profileLayout.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {

            }
        });

        groupsLayout.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {

            }
        });

        tutorialLayout.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {

            }
        });

        logoutLayout.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                setContentView(R.layout.login_page);
            }
        });

        tempLoginLayout.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
//                setContentView(R.layout.login_page);

                Intent intent = new Intent(MainActivity.this, StartPage.class);
                startActivity(intent);
            }
        });


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
            Intent intent = new Intent(MainActivity.this, Profile.class);
            startActivity(intent);
        } else if (menuItem.getItemId() == R.id.nav_group) {
        } else if (menuItem.getItemId() == R.id.nav_group_edit) {
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;

    }




}