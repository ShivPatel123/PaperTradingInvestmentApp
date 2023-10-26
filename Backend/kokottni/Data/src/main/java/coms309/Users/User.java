package coms309.Users;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.ManyToOne;

import coms309.Stocks.Stock;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int money;

    private int numStocks;
    private String name;
    private String email;
    private String dob;
    private String username;
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "stock_id")
    private Stock stock;

    @ManyToOne
    @JoinColumn(name = "friend_group_id")
    private FriendGroup friendGroup;

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
    public String getUsername() {return username;}
    public void setUsername(String user){
        this.username = user;
    }
    public String getPassword() {return password;}
    public void setPassword(String pass){
        this.password = pass;
    }

    public String getDob(){return dob;}

    public void setDob(String dob){this.dob = dob;}

    public void setStock(Stock stock){this.stock = stock;}

    public void purchase(int numStocks){
        if(numStocks < 1) return;
        this.numStocks += numStocks;
        int change = (int) (this.stock.getCurrValue() * numStocks);
        if(change > money) return;
        money -= change;
    }

    public void sellStocks(int numStocks){
        if(numStocks > this.numStocks) return;
        this.numStocks -= numStocks;
        money += (int) (this.stock.getCurrValue() * numStocks);
    }



}
