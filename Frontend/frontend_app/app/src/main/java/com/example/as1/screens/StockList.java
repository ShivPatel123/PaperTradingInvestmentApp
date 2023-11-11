package com.example.as1.screens;

import static android.app.PendingIntent.getActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.as1.ExternalControllers.VolleySingleton;
import com.example.as1.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class StockList extends AppCompatActivity {
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stock_list_page);
            // Inflate the layout for this fragment
            LayoutInflater inflater = null;
            ViewGroup container = null;
            view = inflater.inflate(R.layout.stock_list_page, container, false);
            replacement = view.findViewById(R.id.fragList);

            view.findViewById(R.id.renderStocks).setOnClickListener((View.OnClickListener) this);

            allStocks = (Button) view.findViewById(R.id.renderStocks);
            allStocks.setOnClickListener((View.OnClickListener) this);
            t = (TextView) view.findViewById(R.id.testBox);


            for (int i = 1; i < 5; i++) {
//            URL_JSON_OBJECT = new String ("https://jsonplaceholder.typicode.com/users/" + i);
                URL_JSON_OBJECT = new String("http://coms-309-051.class.las.iastate.edu:8080/stocks/" + i);

                makeJsonObjReq();
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getApplicationContext(), android.R.layout.simple_list_item_1, adapList);
            replacement.setAdapter(adapter);

            replacement.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    String temp = stockListNames.get(position);
                    Integer stockPriceTest = mainStockPrice.get(position);
                    Integer stockIdTest = mainStockID.get(position);
                    String tempSymbol = mainStockSymbol.get(position);

                    t.setText(temp);

                    Log.i(temp, "getting somewhere");
                    Log.i(String.valueOf(stockPriceTest), "gett");

                }
            });
        }

        public void onClick(View v) {

            replacement2 = view.findViewById(R.id.fragList);

            //added Array
            ArrayList<String> list = new ArrayList<>();
            CustomAdapter listAdapter = new CustomAdapter(list);
            replacement2.setAdapter(listAdapter);

            for (int i = 0; i < stockListNames.size(); i++) {
                list.add(stockListNames.get(i) + " - " + mainStockSymbol.get(i));
            }
        }


        class CustomAdapter extends BaseAdapter {
            List<String> items;
            List[] fruits;

            public CustomAdapter(List<String> items) {
                super();
                this.items = items;
            }

            @Override
            public int getCount() {
                return items.size();
            }

            @Override
            public Object getItem(int i) {
                return items.get(i);
            }

            @Override
            public long getItemId(int i) {
                return items.get(i).hashCode();
            }

            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                TextView textView = new TextView(getApplicationContext());
                textView.setMinHeight(35);
                textView.setTextSize(35);
                textView.setText(items.get(i));
                return textView;
            }
        }


        private void makeJsonObjReq() {
            JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                    Request.Method.GET,
                    URL_JSON_OBJECT,
                    null, // Pass null as the request body since it's a GET request
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.d("Volley Response", response.toString());
//                        stockName.setText(response.toString());

                            try {
                                // Parse JSON object data

                                //changed from name
                                String name = response.getString("company");
                                Integer id = response.getInt("id");
                                Integer stockValue = response.getInt("currValue");
                                String symbol = response.getString("symbol");
//                            String email = response.getString("email");
//                            String phone = response.getString("phone");

                                // Populate text views with the parsed data
//                            stockName.setText(name);
//                            testName.setText("200");
//                            testPhone.setText("195");
//                            t = (TextView) view.findViewById(R.id.testBox);
//
                                t.setText(name);
                                stockListNames.add(t.getText().toString());
                                mainStockPrice.add(stockValue);
                                mainStockID.add(id);
                                mainStockSymbol.add(symbol);
                                mainStockList.add(t.getText().toString());

                                Log.d(t.getText().toString(), "test");

//                            testLow.setText(phone);
//                            testHigh.setText(name);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("Volley Error", error.toString());
                        }
                    }) {};

            // Adding request to request queue
            VolleySingleton.getInstance(this.getApplicationContext()).addToRequestQueue(jsonObjReq);
        }


    }
