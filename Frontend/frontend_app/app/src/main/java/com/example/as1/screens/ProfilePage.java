package com.example.as1.screens;


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

public class ProfilePage extends AppCompatActivity {

    private static final String URL_IMAGE ="";
    ImageView imageView;
    Button backHome_btn = findViewById(R.id.backHome_mainBtn);
    Button editProfile_btn = findViewById(R.id.editProfile_btn);
    Button toStock_btn = findViewById(R.id.toStockPage_btn);
    TextView welcomeTxt = findViewById(R.id.welcome_txtView);
    TextView username_display = findViewById(R.id.username_Display);
    TextView password_display = findViewById(R.id.password_Display);
    TextView email_display = findViewById(R.id.email_Display);
    TextView dob_display = findViewById(R.id.dob_Display);
    TextView money_display = findViewById(R.id.money_Display);
    TextView stock_display = findViewById(R.id.stock_Display);
    User testUser = new User(4,7765, 12, "Skyler", "sky@iastate.edu", "yup", "Skyler", "SkylersPassword");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_page);

        welcomeTxt.setText("Welcome, " + testUser.getName() + "!");
        username_display.setText(testUser.getUsername().toString());
        password_display.setText(testUser.getPassword().toString());
        email_display.setText(testUser.getEmail().toString());
        dob_display.setText(testUser.getDob().toString());
        money_display.setText(testUser.getMoney());
        stock_display.setText(testUser.getNumStocks());

    }

    /**
     * Making image GET request
     * */
    private void ImageGetRequest() {

        ImageRequest imageRequest = new ImageRequest(
                URL_IMAGE,
                (Response.Listener<Bitmap>) response -> {
                    //on response
                },
                0, // Width, set to 0 to get the original width
                        0, // Height, set to 0 to get the original height
                        ImageView.ScaleType.FIT_XY, // ScaleType
                        Bitmap.Config.RGB_565, // Bitmap config

                (Response.ErrorListener) error -> {
                    // Handle errors here
                    Log.d("Volley Error", error.toString());
                }
        );

        // Adding request to request queue
        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(imageRequest);
    }

    /**
     * Making image POST request
     * */
    private void ImagePostRequest() {

        ImageRequest imageRequest = new ImageRequest(
                URL_IMAGE,
                (Response.Listener<Bitmap>) response -> {
                    //on response
                },
                0, // Width, set to 0 to get the original width
                0, // Height, set to 0 to get the original height
                ImageView.ScaleType.FIT_XY, // ScaleType
                Bitmap.Config.RGB_565, // Bitmap config

                (Response.ErrorListener) error -> {
                    // Handle errors here
                    Log.d("Volley Error", error.toString());
                }
        );

        // Adding request to request queue
        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(imageRequest);
    }
}