package com.example.as1.Controllers;

import java.util.ArrayList;
import java.util.List;

public class UserSingleton extends User{


    private List<StockPurchased> stocks = new ArrayList<>();
    private long id;
    private double money;
    private String name;
    private String email;
    private String dob;
    private String username;
    private String password;
    private static UserSingleton instance;
    private UserSingleton(long id, double money, String name, String email, String dob, String username, String password){
        this.id = id;
        this.money = money;
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.username = username;
        this.password = password;
    }

    public static synchronized UserSingleton getGlobalUser() {
        if (instance == null) {
            return null;
        }
        return instance;
    }

    public static UserSingleton setGlobalUser(User globalUser) {
//            instance.setGlobalDob(globalUser.getDob());
//            instance.setGlobalId(globalUser.getId());
//            instance.setGlobalName(globalUser.getName());
//            instance.setGlobalEmail(globalUser.getEmail());
//            instance.setGlobalUsername(globalUser.getUsername());
//            instance.setGlobalPassword(globalUser.getPassword());
//            instance.setGlobalMoney(globalUser.getMoney());
       return null;
    }

    //Getters and Setters
//    public static long getGlobalId(){
//        return instance.id;
//    }
//
//    public long setGlobalId(long id){
//        this.id = id;
//    }
//    public double getGlobalMoney(){  return money; }
//    public void setGlobalMoney(double money){   this.money = money; }
//
//    public String getGlobalName(){
//        return name;
//    }
//
//    public void setGlobalName(String name){
//        this.name = name;
//    }
//
//    public String getGlobalEmail(){
//        return email;
//    }
//
//    public void setGlobalEmail(String email){
//        this.email = email;
//    }
//
//    public String getGlobalUsername() {   return username; }
//
//    public void setGlobalUsername(String user){
//        this.username = user;
//    }
//
//    public String getGlobalPassword() {   return password; }
//
//    public void setGlobalPassword(String pass){
//        this.password = pass;
//    }
//
//    public String getGlobalDob(){ return dob; }
//
//    public void setGlobalDob(String dob){ this.dob = dob; }
//
//    public int getGlobalNumStocksPurchased(){return stocks.size() - 1;}

}
