package com.example.as1;

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
    public User(int id, int money, int numStocks, String name, String email, String dob, String username, String password){
        this.id = id;
        this.money = money;
        this.numStocks = numStocks;
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
    public String getMoney(){  return String.valueOf(money); }
    public int getMoneyInt(){  return money; }
    public void setMoney(int money){   this.money = money; }
    public void setMoneyString(String money){   this.money = Integer.parseInt(money); }

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

    public void setNumStocks(int stocks)    {this.numStocks=stocks;}
    public int getNumStocks()     {return this.numStocks;}


}//end

