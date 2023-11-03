package com.example.poop4;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.example.poop4.databinding.ActivityMainBinding;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ListView;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ListAdapter;
import android.widget.BaseAdapter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import androidx.appcompat.app.AppCompatActivity;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import java.net.URI;
import org.java_websocket.handshake.ServerHandshake;
import org.java_websocket.client.WebSocketClient;
import org.w3c.dom.Text;
import java.util.Date;

import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.view.LayoutInflater;
import android.view.ViewGroup;



/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    /* Parameters for single stock*/
    public int actualStockNumber;
    public String actualStockName;

    public int actualCurrentPrice;

    public int actualPastPrice;

    public int actualStockHigh;

    public int actualStockLow;

    public View homeView;

    private TextView blankStockName;

    private TextView blankCurrentPrice;

    private TextView blankPreviousPrice;

    private TextView blankStockHigh;

    private TextView blankStockLow;

    public Button addRemove;

    private ListView homeListView;

    public HomeFragment() {
        // Required empty public constructor
    }

    public HomeFragment(int stockNumber, String stockName, int currentPrice, int pastPrice, int stockHigh, int stockLow){
        actualStockNumber = stockNumber;
        actualStockName = stockName;
        actualCurrentPrice = currentPrice;
        actualPastPrice = pastPrice;
        actualStockHigh = stockHigh;
        actualStockLow = stockLow;

        //Type X;
        //X = findViewById(R.id.?);
        //X.setText();


    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        homeView = inflater.inflate(R.layout.fragment_home, container, false);
//        homeListView = homeView.findViewById(R.id.fragList);


        addRemove = (Button) homeView.findViewById(R.id.addRemoveButton);
        addRemove.setOnClickListener(this);


        blankStockName = homeView.findViewById(R.id.stockNameTextView);
        blankCurrentPrice = homeView.findViewById(R.id.testEmail);
        blankPreviousPrice = homeView.findViewById(R.id.testPrice);
        blankStockHigh = homeView.findViewById(R.id.stockHigh);
        blankStockLow = homeView.findViewById(R.id.stockLow);

        blankStockName.setText(actualStockName);
        blankCurrentPrice.setText(String.valueOf(actualCurrentPrice));
        blankPreviousPrice.setText(String.valueOf(actualPastPrice));
        blankStockHigh.setText(String.valueOf(actualStockHigh));
        blankStockLow.setText(String.valueOf(actualStockLow));





//        return inflater.inflate(R.layout.fragment_home, container, false);
        return homeView;
    }



    @Override
    public void onClick(View v) {

    }





    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.addRemoveButton).setOnClickListener(this);

    }




}