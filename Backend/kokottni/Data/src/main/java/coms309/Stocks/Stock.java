package coms309.Stocks;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import coms309.Users.User;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

@Entity
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String symbol;
    private String company;
    private double currValue;
    private double prevDayChange;

    @ManyToMany
    @JsonIgnore
    private Map<Integer, User> users;

    public Stock(int id, String symbol, String company, double currValue, double prevDayChange){
        this.id = id;
        this.symbol = symbol;
        this.company = company;
        this.currValue = currValue;
        this.prevDayChange = prevDayChange;
    }

    public Stock(){}

    public int getStockId(){return id;}
    public void setStockId(int id){this.id = id;}
    public String getSymbol(){return  symbol;}
    public void setSymbol(String symbol){this.symbol = symbol;}
    public String getCompany(){return company;}
    public void setCompany(String company){this.company = company;}
    public double getCurrValue(){return currValue;}
    public void setCurrValue(double currValue){this.currValue = currValue;}
    public double getPrevDayChange(){return prevDayChange;}
    public void setPrevDayChange(double prevDayChange){this.prevDayChange = prevDayChange;}
    public User getUser(int id){return users.get(id);}
    public void setUser(User user){this.users.put(user.getId(), user);}
}
