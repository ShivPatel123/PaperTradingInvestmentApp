package com.example.as1.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.as1.ExternalControllers.VolleySingleton;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.as1.R;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment implements View.OnClickListener {


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //    private static final String URL_JSON_OBJECT = "https://jsonplaceholder.typicode.com/users/";
//    private String URL_JSON_OBJECT = "https://jsonplaceholder.typicode.com/users/";
    private String URL_JSON_OBJECT = "http://coms-309-051.class.las.iastate.edu:8080/stock/";
//    public String adapList[] = {"Apple", "Banana", "Orange", "Watermelon"};

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


    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        replacement = view.findViewById(R.id.fragList);

        allStocks = (Button) view.findViewById(R.id.renderStocks);
        allStocks.setOnClickListener(this);
        t = (TextView) view.findViewById(R.id.testBox);


        for (int i = 1; i < 5; i++) {
//            URL_JSON_OBJECT = new String ("https://jsonplaceholder.typicode.com/users/" + i);
            URL_JSON_OBJECT = new String("http://coms-309-051.class.las.iastate.edu:8080/stocks/" + i);

            makeJsonObjReqq();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, adapList);
        replacement.setAdapter(adapter);

        replacement.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//                int temp = position+1;
//
//                URL_JSON_OBJECT = new String ("https://jsonplaceholder.typicode.com/users/" + temp);
//                makeJsonObjReqq();
//
//                String temp2 = t.getText().toString();
//                t.setText("UMTien" + temp + temp2);
//                Log.d(t.getText().toString(), "wat");


                String temp = stockListNames.get(position);
                Integer stockPriceTest = mainStockPrice.get(position);
                Integer stockIdTest = mainStockID.get(position);
                String tempSymbol = mainStockSymbol.get(position);

                t.setText(temp);

                Log.d(temp, "getting somewhere");
                Log.d(String.valueOf(stockPriceTest), "gett");

//    public HomeFragment(int stockNumber, String stockName, int currentPrice, int pastPrice, int stockHigh, int stockLow){

                replaceFragment(new HomeFragment(stockPriceTest, temp, stockPriceTest, stockIdTest, stockPriceTest + 5, stockPriceTest - 7));
            }
        });


        return view;
    }


    @Override
    public void onClick(View v) {

        //t.setText("UMTien");
        replacement2 = view.findViewById(R.id.fragList);

        //added Array
        ArrayList<String> list = new ArrayList<>();
        CustomAdapter listAdapter = new CustomAdapter(list);
        replacement2.setAdapter(listAdapter);

        for (int i = 0; i < stockListNames.size(); i++) {
            list.add(stockListNames.get(i) + " - " + mainStockSymbol.get(i));
            Log.d("hi", "WHY FUCK" + i);
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.renderStocks).setOnClickListener(this);

//        // or
//        getActivity().findViewById(R.id.yourId).setOnClickListener(this);
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
            TextView textView = new TextView(getContext());
            textView.setMinHeight(35);
            textView.setTextSize(35);
            textView.setText(items.get(i));
//            textView.setMinHeight(25);
            return textView;
        }
    }


    private void makeJsonObjReqq() {
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
                }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
//                headers.put("Authorization", "Bearer YOUR_ACCESS_TOKEN");
//                headers.put("Content-Type", "application/json");
                return headers;
            }

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
//                params.put("param1", "value1");
//                params.put("param2", "value2");
                return params;
            }
        };

        // Adding request to request queue
        VolleySingleton.getInstance(getActivity().getApplicationContext()).addToRequestQueue(jsonObjReq);
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

}


