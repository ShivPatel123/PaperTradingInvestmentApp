package com.example.as1.screens;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.as1.R;
import com.example.as1.User;

public class EditProfilePage extends AppCompatActivity {

//TODO: need universal user ID
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profile);

        //Instantiate Buttons
        //private static final String URL_IMAGE = "";
        Button back_btn = findViewById(R.id.EbackProfile_btn);
        Button saveProfile_btn = findViewById(R.id.saveProfile_btn);
        Button toStock_btn = findViewById(R.id.toStockPage_btn);
        EditText welcomeTxt = findViewById(R.id.Ewelcome_txtView);
        EditText username_display = findViewById(R.id.Eusername_Display);
        EditText password_display = findViewById(R.id.Epassword_Display);
        EditText email_display = findViewById(R.id.Eemail_Display);
        EditText dob_display = findViewById(R.id.Edob_Display);
        EditText money_display = findViewById(R.id.Emoney_Display);
        User testUser = new User(4, 7765, 12, "Skyler", "sky@iastate.edu", "yup", "Skyler", "SkylersPassword");

        //set text to initial profile data
        welcomeTxt.setText(testUser.getName().toString());
        username_display.setText(testUser.getUsername().toString());
        password_display.setText(testUser.getPassword().toString());
        email_display.setText(testUser.getEmail().toString());
        dob_display.setText(testUser.getDob().toString());
        money_display.setText(testUser.getMoney().toString());

        // save changes button
        saveProfile_btn.setOnClickListener(view -> {
            testUser.setName(welcomeTxt.getText().toString());
            testUser.setUsername(username_display.getText().toString());
            testUser.setPassword(password_display.getText().toString());
            testUser.setEmail(email_display.getText().toString());
            testUser.setDob(dob_display.getText().toString());
            testUser.setMoneyString(money_display.getText().toString());
            //TODO: pass onrepsonse function and error function as params in gerneric post and get req fucntions

        });

        //back button
        back_btn.setOnClickListener(view -> {
            Intent intent = new Intent(EditProfilePage.this, ProfilePage.class);
            startActivity(intent);
        });


    }
}