package com.example.as1.screens;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.as1.R;
import com.example.as1.Controllers.User;
import com.example.as1.ExternalControllers.VolleySingleton;

import org.json.JSONException;

public class ProfilePage extends AppCompatActivity {

    private static final String URL_IMAGE ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_page);

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

        //Get global user data for get request (just need id for get req)
        User getGlobal = User.getInstance();
        //Get req for user data, need to be sure global user has id set after logging in
        getGlobal = getUserData(this.getApplicationContext(),getGlobal);
        //TODO: update global user data (may need to make function)

        //set display to user data
        welcomeTxt.setText("Welcome, " + getGlobal.getName() + "!");
        username_display.setText(getGlobal.getUsername());
        password_display.setText(getGlobal.getPassword());
        email_display.setText(getGlobal.getEmail());
        dob_display.setText(getGlobal.getDob());
        double money1 = getGlobal.getMoney();
        money_display.setText(String.valueOf(money1));
        //stock_display.setText(getGlobal.getNumStocksPurchased());

        //Back to Home page button
        backHome_btn.setOnClickListener(view -> {
            Intent intent = new Intent(ProfilePage.this, MainPage.class);
            startActivity(intent);
        });

        //Edit Profile button
        editProfile_btn.setOnClickListener(view -> {
            Intent intent = new Intent(ProfilePage.this, EditProfilePage.class);
            startActivity(intent);
        });

        //To Stock Page button
        toStock_btn.setOnClickListener(view -> {
            Intent intent = new Intent(ProfilePage.this, PortfolioPage.class);
            startActivity(intent);
        });

    }

    public User getUserData(Context context, User user) {
        String URL_JSON_OBJECT = "http://10.90.75.130:8080/user/".concat(String.valueOf(user.getId()));

        //Create new request
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                URL_JSON_OBJECT,
                null,
                response -> {
                    try {
                        // Parse JSON object data
                        String name = response.getString("name");
                        String email = response.getString("email");
                        String id = response.getString("id");
                        String dob = response.getString("dob");
                        String money = response.getString("money");
                        //TODO: String numStocks = response.getJSONArray("numStocks");
                        String username = response.getString("username");
                        String password = response.getString("password");

                        // Populate text views with the parsed data
                        user.setName(name);
                        user.setEmail(email);
                       // user.setId(Integer.parseInt(id));
                        user.setDob(dob);
                        user.setMoney(Double.parseDouble(money));
                        // user.setNumStocks(Integer.parseInt(numStocks));
                        user.setUsername(username);
                        user.setPassword(password);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> Log.d("error msg", "getUserData: " + error.getMessage())) {
        };

        // Adding request to request queue
        VolleySingleton.getInstance(context.getApplicationContext()).addToRequestQueue(request);
        return user;
    }





    //TODO:Image get and post to set profile picture
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