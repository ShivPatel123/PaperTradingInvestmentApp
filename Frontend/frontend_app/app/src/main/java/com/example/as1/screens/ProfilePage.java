package com.example.as1.screens;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Response;
import com.android.volley.toolbox.ImageRequest;
import com.example.as1.R;
import com.example.as1.VolleySingleton;

public class ProfilePage extends AppCompatActivity {

    private static final String URL_IMAGE ="";
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_page);
//display edit profile
        //make button to go to portfolio page for stocks/money info
    }

    /**
     * Making image request
     * */
    private void makeImageRequest() {

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