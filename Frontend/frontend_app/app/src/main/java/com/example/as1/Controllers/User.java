package com.example.as1.Controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * User object
 * Mirrors backend User class
 */
public class User {

    /**
     * User singleton for global user instance
     */
    private static User instance = null;
    public static User getInstance(){
        if (instance == null){
            instance = new User(-1,-1,"NoInputs", "NoInputs", "NoInputs", "NoInputs", "NoInputs");
        }
        return instance;
    }

    /**
     * List of purchased stocks
     */
    private List<StockPurchased> stocks = new ArrayList<>();
    //Params
    private long id;
    private double money;
    private String name;
    private String email;
    private String dob;
    private String username;
    private String password;

    /**
     * User constructor
     * @param id User ID
     * @param money Amount of money User has
     * @param name User's name
     * @param email User's email
     * @param dob User's DOB
     * @param username Login Username
     * @param password Login Password
     */
    public User(long id, double money, String name, String email, String dob, String username, String password){
        this.id = id;
        this.money = money;
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.username = username;
        this.password = password;
    }
    public User(){}

    //Getters and Setters

    /**
     * ID Getter
     * @return long id
     */
    public long getId(){
        return id;
    }

    /**
     * ID Setter
     * @param id long id
     */
    public void setId(long id){
        this.id = id;
    }

    /**
     * Money Getter
     * @return double money
     */
    public double getMoney(){  return money; }

    /**
     * Money Setter
     * @param money double money
     */
    public void setMoney(double money){   this.money = money; }

    /**
     * Name Getter
     * @return String name
     */
    public String getName(){
        return name;
    }

    /**
     * Name Setter
     * @param name String name
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Email Getter
     * @return String email
     */
    public String getEmail(){
        return email;
    }

    /**
     * Email Setter
     * @param email String email
     */
    public void setEmail(String email){
        this.email = email;
    }

    /**
     * Username Getter
     * @return String username
     */
    public String getUsername() {   return username; }

    public void setUsername(String user){
        this.username = user;
    }

    /**
     * Password Setter
     * @return String password
     */
    public String getPassword() {   return password; }

    /**
     * Password Setter
      * @param pass String password
     */
    public void setPassword(String pass){
        this.password = pass;
    }

    /**
     * DOB Getter
     * @return String DOB
     */
    public String getDob(){ return dob; }

    /**
     * DOB Setter
     * @param dob String dob
     */
    public void setDob(String dob){ this.dob = dob; }

    /**
     * Num Stocks Getter
     * @return Size of Stocks Purchased array (-1)
     */
    public int getNumStocksPurchased(){return stocks.size() - 1;}

    /**
     * Function to buy stock
     * @param stock Stock
     * @param numPurchase Number of stocks to purchase
     * @param id ID of stock being purchased
     * @return StockPurchased
     */
    public StockPurchased setStock(Stock stock, int numPurchase, Long id){
        for(int i = 0; i <  stocks.size(); ++i){
            if(stock.getId().equals(stocks.get(i).getStock().getId()) && money >= numPurchase * stock.getCurrValue()){
                StockPurchased toRemove = stocks.get(i);
                stocks.get(i).setNumPurchased(stocks.get(i).getNumPurchased() + numPurchase);
                stocks.get(i).setCostPurchase(stocks.get(i).getCostPurchase() + stocks.get(i).getStock().getCurrValue() * numPurchase);
                return toRemove;
            }
        }
        StockPurchased curr = new StockPurchased();
        curr.setStock(stock);
        curr.setUser(this);
        curr.setId(id);
        curr.setNumPurchased(numPurchase);
        curr.setSinglePrice(stock.getCurrValue());
        curr.setCostPurchase(curr.getNumPurchased() * stock.getCurrValue());

        if( curr.getNumPurchased() * stock.getCurrValue() > money) return null;
        stocks.add(curr);
        stock.setUser(this, numPurchase, id);
        money -= curr.getCostPurchase();
        return curr;
    }

    /**
     * Remove a stock from user's stockPurchased list
     * @param numStocks Number of stocks to be removed
     * @param stock Stock to be removed
     * @return null
     */
    public StockPurchased removeStocks(int numStocks, Stock stock){
        StockPurchased potentiallyRemoved;
        for(int i = 0; i < stocks.size(); ++i){
            if(stock.getId().equals(stocks.get(i).getStock().getId())){
                if(numStocks >= stocks.get(i).getNumPurchased()){
                    double moneyChanged = stocks.get(i).getCostPurchase();
                    money += moneyChanged;
                    potentiallyRemoved = stocks.get(i);
                    stocks.remove(stocks.get(i));
                    return potentiallyRemoved;
                }else{
                    double moneyChanged = stocks.get(i).getSinglePrice() * numStocks;
                    money += moneyChanged;
                    potentiallyRemoved = stocks.get(i);
                    stocks.get(i).setNumPurchased(stocks.get(i).getNumPurchased() - numStocks);
                    stocks.get(i).setCostPurchase(stocks.get(i).getCostPurchase() - numStocks * stocks.get(i).getSinglePrice());
                    return potentiallyRemoved;
                }
            }
        }
        return null;
    }

    public List<StockPurchased> getStocks(){return stocks;}

    public void setStocks(List<StockPurchased> stocksIn){this.stocks = stocksIn;}

    public StockPurchased purchase(int numStocks, Stock stock, long id){
        if(numStocks < 1 || stock == null) return null;
        return setStock(stock, numStocks, id);
    }

    public StockPurchased sell(int numStocks, Stock stock){
        if(numStocks < 1 || stock == null) return null;
        return removeStocks(numStocks, stock);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        StockPurchased other = (StockPurchased) obj;
        if (Objects.equals(id, null)) {
            return other.getId() == null;
        } else return Objects.equals(id, other.getId());
    }



}//end

