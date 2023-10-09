package com.example.androidexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
public class User {

    //Params
    private int id;
    private int money;
    private int numStocks;
    private String name;
    private String email;
    private String dob;
    private String username;
    private String password;

//Constructors
    public User(int id, int money, String name, String email, String dob, String username, String password){
        this.id = id;
        this.money = money;
        this.numStocks = 0;
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.username = username;
        this.password = password;
    }
    public User(){}

    //Getters and Setters
    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }
    public int getMoney(){  return money; }
    public void setMoney(int money){    this.money = money; }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getUsername() {   return username; }

    public void setUsername(String user){
        this.username = user;
    }

    public String getPassword() {   return password; }
    public void setPassword(String pass){
        this.password = pass;
    }

    public String getDob(){ return dob; }

    public void setDob(String dob){ this.dob = dob; }


}//end
