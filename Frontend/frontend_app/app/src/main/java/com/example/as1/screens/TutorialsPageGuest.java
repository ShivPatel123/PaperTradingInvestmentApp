package com.example.as1.screens;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.as1.Controllers.User;
import com.example.as1.R;
import com.google.android.material.navigation.NavigationView;


public class TutorialsPageGuest extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    protected Button newStock_btn, pageLeft, pageRight, buy_btn, sell_btn, delete_btn, toNews_btn;
    protected TextView stockName, stockSymbol, serverNotes;
    protected EditText curr_display;

    protected TextView id_display, stockChange;
    ImageView stockImage;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Menu menu;
    ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tutorial_page_guest);
        final int[] index = {0};



        //Side nav bar
        drawerLayout = findViewById(R.id.drawer_layout_stock);
        navigationView = findViewById(R.id.nav_view_stock);
        menu = navigationView.getMenu();
        menu.findItem(R.id.nav_logout).setVisible(false);
        menu.findItem(R.id.nav_profile).setVisible(false);
        menu.findItem(R.id.nav_group).setVisible(false);
        menu.findItem(R.id.nav_group_edit).setVisible(false);

        navigationView.bringToFront();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        toolbar.setSubtitle("");
        setSupportActionBar(toolbar);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);

        // to make the Navigation drawer icon always appear on the action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);




        User getGlobal = User.getInstance();


        //Page Right Button
        pageRight = findViewById(R.id.next_StockPageBtn);

        pageRight.setOnClickListener(view -> {
            Intent intent = new Intent(TutorialsPageGuest.this, StartPage.class);
            startActivity(intent);
        });

        pageLeft = findViewById(R.id.prev_StockPageBtn);

        pageLeft.setOnClickListener(view -> {
            Intent intent = new Intent(TutorialsPageGuest.this, MainActivity.class);
            startActivity(intent);
        });

    }



    /*
        Nav Bar Functions
     */
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
            Intent intent = new Intent(TutorialsPageGuest.this, MainActivity.class);
            startActivity(intent);
        } else if (menuItem.getItemId() == R.id.nav_stock){
            Intent intent = new Intent(TutorialsPageGuest.this, StockPageGuest.class);
            startActivity(intent);
        } else if (menuItem.getItemId() == R.id.nav_stock_list) {
            Intent intent = new Intent(TutorialsPageGuest.this, StockListGuest.class);
            startActivity(intent);
        } else if (menuItem.getItemId() == R.id.nav_login) {
            Intent intent = new Intent(TutorialsPageGuest.this, StartPage.class);
            startActivity(intent);
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}

