package com.example.as1.screens;

import static com.example.as1.Controllers.User.getInstance;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.as1.Controllers.RecycleViews.StockScrollAdapter;
import com.example.as1.Controllers.RecycleViews.StockScrollCard;
import com.example.as1.Controllers.User;
import com.example.as1.ExternalControllers.VolleySingleton;
import com.example.as1.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class StockListGuest extends AppCompatActivity {
    private String URL_JSON_OBJECT = "http://coms-309-051.class.las.iastate.edu:8080/stock/";
    public String adapList[] = {"Please Load Stocks:"};
    public ArrayList<String> stockListNames = new ArrayList<String>();
    private ListView replacement;
    private ListView replacement2;
    public ArrayList<String> mainStockList = new ArrayList<String>();
    public ArrayList<Integer> mainStockPrice = new ArrayList<Integer>();
    public ArrayList<Integer> mainStockID = new ArrayList<Integer>();
    public ArrayList<String> mainStockSymbol = new ArrayList<String>();

    public View view;
    public TextView t;
    public Button allStocks;

    public Button sellBtn;

    public Button buyBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stock_list_page_guest);

        //get all stocks from server
        getAllStocks(this.getApplicationContext());


//        R.layout.stock_page_guest
//        Button sellBtn = findViewById(R.layout.stock_preview_scroll);
//
//        LayoutInflater inflater = this.getLayoutInflater();
//        ViewGroup mainLayout = findViewById(R.id.sellStock_btn);
//        ConstraintLayout v = (ConstraintLayout) inflater.inflate(R.layout.stock_preview_scroll, com.example.navviewfe.Controllers.RecycleViews.StockScrollAdapter.class, true);
//        Button bt1 = (Button)v.findViewById(R.id.sellStock_btn);
////
//        bt1.setOnClickListener(view -> {
//            Intent intent = new Intent(StockListGuest.this, MainActivity.class);
//            startActivity(intent);
//        });


//
//        Button bt1 = itemView.findViewById(R.id.sellStock_btn);
//        Button bt2 = itemView.findViewById(R.id.buyStock_btn);
//

//        Button bt1 = findViewById(R.id.sellStock_btn);



//        // Inflate the layout containing the button
//        View view = getLayoutInflater().inflate(R.layout.stock_scroll_view, null, false);
////
////        // Find the button in the inflated layout
//        Button myButton = view.findViewById(R.id.sellStock_btn);
////
////        // Add the inflated view or button to your activity's layout
//        ViewGroup mainLayout = findViewById(R.id.StockList); // Replace with your main layout ID
////        mainLayout.addView(view);


        Button back_Btn = findViewById(R.id.back_StockListBtn);
        back_Btn.setOnClickListener(view -> {
            User user = getInstance();
            Intent intent;
            if(user.getUsername().equals(null)){
                intent = new Intent(StockListGuest.this, StartPage.class);
            }
            else{
//                intent = new Intent(StockList.this, NavPage.class);
                intent = new Intent(StockListGuest.this, MainActivity.class);


            }
            startActivity(intent);

        });
    }

    public void getAllStocks(Context context) {
        String URL_JSON_OBJECT = "http://10.90.75.130:8080/stocks";
        //http://coms-309-051.class.las.iastate.edu:8080/user/

        ArrayList<StockScrollCard> stockCardArrayList= new ArrayList<>();
        RecyclerView recyclerView = findViewById(R.id.stockList_scroll);

        //Create new request
        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET,
                URL_JSON_OBJECT,
                null,
                response -> {
                    Log.i(" full response", "getAllStocks: " + response.toString());

                    JSONObject object;

                    for(int i = 0; i < response.length(); i++) {
                        try {
                            object = (JSONObject) response.get(i);
                            // JSONObject stockObj = (JSONObject) object.get("user");
                            //User userIN = new User();

                            //parse relevant info
                            String name = object.getString("company");
                            name += " " + object.getString("symbol");
                            int prevDay = (int) object.getDouble("prevDayChange");
                            int stockPrice = (int) object.getDouble("currValue");

                            //add to arraylist to be displayed in recycle view
                            //TODO
                            stockCardArrayList.add(new StockScrollCard(name, prevDay, stockPrice, 0, "none"));

                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }

                    }
                    //Initialize recycler view
                    StockScrollAdapter stockScrollAdapter = new StockScrollAdapter(this, stockCardArrayList);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

                    //Set recycler view
                    recyclerView.setLayoutManager(linearLayoutManager);
                    recyclerView.setAdapter(stockScrollAdapter);
                },

                error -> Log.i("Error ", "getAllStocks: "+ error.getMessage())) {};

        // Adding request to request queue
        VolleySingleton.getInstance(context.getApplicationContext()).addToRequestQueue(request);
    }

}
