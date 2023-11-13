package com.example.as1.Controllers;

import java.util.ArrayList;
import java.util.List;

/**
 * Stock object class
 * Mirrors Stock class on backend
 */
public class Stock {

    protected Long id;
    protected String symbol;
    protected String company;
    protected double currValue;
    protected double prevDayChange;

    /**
     * List of users that have purchased the stock
     */
    protected List<StockPurchased> users = new ArrayList<>();

    /**
     * Constructor for a Stock
     * @param id Stock ID
     * @param symbol NASDAQ abbreviation of the Stock's company name
     * @param company Company that owns the stock
     * @param currValue Current value of the stock
     * @param prevDayChange Previous day value of the stock
     */
    public Stock(Long id, String symbol, String company, double currValue, double prevDayChange) {
        this.id = id;
        this.symbol = symbol;
        this.company = company;
        this.currValue = currValue;
        this.prevDayChange = prevDayChange;
    }

    public Stock() {
    }


    /**
     * ID Getter
     * @return long ID
     */
    public Long getId() {
        return id;
    }

    /**
     * ID Setter
     * @param id input long id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Symbol Getter
     * @return string symbol
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * Symbol Setter
     * @param symbol String symbol
     */
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    /**
     * Company Getter
     * @return String of company name
     */
    public String getCompany() {
        return company;
    }

    /**
     * Company Setter
     * @param company String of company name
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * Current value Getter
     * @return double currValue
     */
    public double getCurrValue() {
        return currValue;
    }

    /**
     * Current Value Setter
     * @param currValue double currValue
     */
    public void setCurrValue(double currValue) {
        this.currValue = currValue;
    }

    /**
     * Previous day value Getter
     * @return prevDayChange
     */
    public double getPrevDayChange() {
        return prevDayChange;
    }
    /**
     * Previous day value Setter
     * @param prevDayChange double of value of the previous day change
     */
    public void setPrevDayChange(double prevDayChange) {
        this.prevDayChange = prevDayChange;
    }

    /**
     * Assign a user to a stock by adding the user to the stock's User list
     * @param user User to be assigned to the stock
     * @param numBuying Amount of stocks the user is buying
     * @param id ID of the stock being bought
     */
    public void setUser(User user, int numBuying, Long id) {
        StockPurchased curr = new StockPurchased();
        curr.setId(id);
        curr.setUser(user);
        curr.setStock(this);
        curr.setSinglePrice(currValue);
        curr.setNumPurchased(numBuying);
        curr.setCostPurchase(numBuying * currValue);
        users.add(curr);
    }

    /**
     * Getter for the list of Users that have purchased the stock
     * @return List of StockPurchased
     */
    public List<StockPurchased> getUsers() {
        return users;
    }


}//end
