package com.example.as1.screens;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.as1.ExternalControllers.VolleySingleton;
import com.example.as1.R;

import org.json.JSONException;


public class StockPage extends AppCompatActivity{

    private TextView stockName;
    private String URL_JSON_OBJECT = "http://coms-309-051.class.las.iastate.edu:8080/stock/";
    private TextView testName;
    private TextView testPhone;
    private TextView testLow;
    private TextView testHigh;
    private ListView stockListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stock_page);

        /* initialize UI elements for Home */
        Button add_remove = findViewById(R.id.addRemoveButton);
        stockName = findViewById(R.id.stockNameTextView);
        testName = findViewById(R.id.testEmail);
        testPhone = findViewById(R.id.testPrice);
        testLow = findViewById(R.id.stockLow);
        testHigh = findViewById(R.id.stockHigh);


//        /* initialize UI elements for portfolio */
        stockListView = (ListView) findViewById(R.id.stockListView);
        //TODO : replace test stock with stocks from server
        String[] testStock = new String[0];
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.activity_list_view, R.id.test123, testStock);
        stockListView.setAdapter(arrayAdapter);

        add_remove.setOnClickListener(v -> {
            makeJsonObjReq();
        });
    }

    private void makeJsonObjReq() {
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                Request.Method.GET,
                URL_JSON_OBJECT,
                null, // Pass null as the request body since it's a GET request
                response -> {
                    Log.i("Volley Response", response.toString());
                    stockName.setText(response.toString());

                    try {
                        // Parse JSON object data
                        String name = response.getString("name");
                        String email = response.getString("email");
                        String phone = response.getString("phone");

                        // Populate text views with the parsed data
                        stockName.setText(name);
                        testName.setText("200");
                        testPhone.setText("195");

                        testLow.setText(phone);
                        testHigh.setText(name);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> Log.i("Volley Error", error.toString()));

        // Adding request to request queue
        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonObjReq);
    }

}






