package com.example.as1.screens;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.as1.Controllers.RecycleViews.HistoryAdpater;
import com.example.as1.Controllers.RecycleViews.HistoryScrollCard;
import com.example.as1.ExternalControllers.VolleySingleton;
import com.example.as1.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class HistoryPageGuest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_page_guest);

        Intent historyIntent = getIntent();
        int id = historyIntent.getIntExtra("index", 1);
        Log.i("get Intent", " id: " + id);

        //Initialize recycler view (user)
        RecyclerView recyclerView = findViewById(R.id.history_recyclerView);
        ArrayList<HistoryScrollCard> CardArrayList = new ArrayList<>();
        CardArrayList.add(new HistoryScrollCard("2012-1-1", "420.00", "430.00", "440.00", "450.00"));
        HistoryAdpater historyAdapter = new HistoryAdpater(this, CardArrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(historyAdapter);
        //get all stock history from server
        getStockHistory(this.getApplicationContext(), id);

        Button back_Btn = findViewById(R.id.back_Historybtn);
        back_Btn.setOnClickListener(view -> {
            Intent intent= new Intent(HistoryPageGuest.this, StockPageGuest.class);
            startActivity(intent);
        });
    }

    public void getStockHistory(Context context, int id) {
        String URL_JSON_OBJECT = "http://10.90.75.130:8080/stocks/history/" + id;

        ArrayList<HistoryScrollCard> CardArrayList= new ArrayList<>();
        RecyclerView recyclerView = findViewById(R.id.history_recyclerView);

        //Create new request
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                URL_JSON_OBJECT,
                null,
                response -> {
                    Log.i(" full response", "getHistory: " + response);
                    try {
                        JSONObject timeSeries = (JSONObject) response.get("Weekly Time Series");
                        Log.i(" timeSeries", "getHistory: " + timeSeries);
                        Log.i(" timeSeries keys", "getHistory: " +  timeSeries.keys());
                        Iterator<String> iterator = timeSeries.keys();

                        JSONObject object;
                        while(iterator.hasNext()) {
                            String date = iterator.next();
                            object = timeSeries.getJSONObject(date);

                            //parse relevant info
                            String open, close, high, low;
                            open = object.getString("1. open");
                            close = object.getString("4. close");
                            high = object.getString("2. high");
                            low = object.getString("3. low");

                            CardArrayList.add(new HistoryScrollCard(date, open, close, high, low));
                        }

                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                    //Initialize recycler view
                    HistoryAdpater historyAdapter = new HistoryAdpater(this, CardArrayList);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

                    //Set recycler view
                    recyclerView.setLayoutManager(linearLayoutManager);
                    recyclerView.setAdapter(historyAdapter);
                },

                error -> Log.i("Error ", "getStockHistory: "+ error)) {};

        // Adding request to request queue
        VolleySingleton.getInstance(context.getApplicationContext()).addToRequestQueue(request);
    }

}
