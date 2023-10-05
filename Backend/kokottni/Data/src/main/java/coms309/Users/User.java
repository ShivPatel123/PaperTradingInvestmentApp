package coms309.Users;

import javax.persistence.*;

import coms309.AmountPurchased;
import coms309.Stocks.Stock;

import java.util.ArrayList;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int money;
    private String name;
    private String email;
    private String dob;

    @ManyToMany
    @JoinTable(
            name = "purchased_stocks",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "stockId")
    )
    private ArrayList<Stock> purchasedStocks = new ArrayList<>();

    public User(int id, int money, String name, String email, String dob){
        this.id = id;
        this.money = money;
        this.name = name;
        this.email = email;
        this.dob = dob;
    }

    public User(){}

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getMoney(){return money;}

    public void setMoney(int money){this.money = money;}

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

    public String getDob(){return dob;}

    public void setDob(String dob){this.dob = dob;}

    public Stock getStock(int id){return purchasedStocks.get(id);}

    public void setStock(Stock stock){this.purchasedStocks.add(stock);}
}
