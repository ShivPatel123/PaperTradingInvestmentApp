package com.example.as1.screens;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Response;
import com.android.volley.toolbox.ImageRequest;
import com.example.as1.R;
import com.example.as1.User;
import com.example.as1.VolleySingleton;

public class EditProfilePage extends AppCompatActivity {

    //private static final String URL_IMAGE = "";
    Button back_btn = findViewById(R.id.backProfile_btn);
    Button saveProfile_btn = findViewById(R.id.saveProfile_btn);
    Button toStock_btn = findViewById(R.id.toStockPage_btn);
    TextView welcomeTxt = findViewById(R.id.welcome_txtView);
    TextView username_display = findViewById(R.id.username_Display);
    TextView password_display = findViewById(R.id.password_Display);
    TextView email_display = findViewById(R.id.email_Display);
    TextView dob_display = findViewById(R.id.dob_Display);
    TextView money_display = findViewById(R.id.money_Display);
    TextView stock_display = findViewById(R.id.stock_Display);
    User testUser = new User(4, 7765, 12, "Skyler", "sky@iastate.edu", "yup", "Skyler", "SkylersPassword");
//TODO: need universal user ID
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profile);

        welcomeTxt.setText(testUser.getName().toString());
        username_display.setText(testUser.getUsername().toString());
        password_display.setText(testUser.getPassword().toString());
        email_display.setText(testUser.getEmail().toString());
        dob_display.setText(testUser.getDob().toString());
        money_display.setText(testUser.getMoney());
        stock_display.setText(testUser.getNumStocks());


        // save changes button
        saveProfile_btn.setOnClickListener(view -> {
            testUser.setName(welcomeTxt.getText().toString());
            testUser.setUsername(username_display.getText().toString());
            testUser.setPassword(password_display.getText().toString());
            testUser.setEmail(email_display.getText().toString());
            testUser.setDob(dob_display.getText().toString());
          //TODO: need to get int testUser.setMoney(money_display.getText());
        });

        //back button
        back_btn.setOnClickListener(view -> {
            Intent intent = new Intent(EditProfilePage.this, ProfilePage.class);
            startActivity(intent);
        });


    }
}