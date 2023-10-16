package coms309.Users;

import javax.persistence.*;

import coms309.Stocks.Stock;
import coms309.Stocks.StockPurchased;

import java.util.Set;

@Entity
@Table(name = "user")
public class User {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "money")
    private int money;

    @ManyToMany
    @JoinTable(name = "owned_stocks", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "stock_id"))
    private Set<Stock> stocksOwned;

    @OneToMany(mappedBy = "user")
    private Set<StockPurchased> amountPurchased;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "date_of_birth")
    private String dob;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;


    public User(Long id, int money, String name, String email, String dob, String username, String password){
        this.id = id;
        this.money = money;
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.username = username;
        this.password = password;
    }

    public User(){}

    public Long getId(){
        return id;
    }

    public void setId(Long id){
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

    public void setStock(Stock stock){
        stocksOwned.add(stock);
        StockPurchased curr = new StockPurchased();
        curr.setStock(stock);
        curr.setUser(this);
        curr.setNumPurchased(1);
        curr.setCostPurchase(curr.getNumPurchased() * stock.getCurrValue());
        amountPurchased.add(curr);
    }

//    public void purchase(int numStocks, Stock stock){
//        if(numStocks < 1 || stock == null) return;
//        amountPurchased.
//        int change = (int) (this.stock.getCurrValue() * numStocks);
//        if(change > money) return;
//        money -= change;
//    }
//
//    public void sellStocks(int numStocks){
//        if(numStocks > this.numStocks) return;
//        this.numStocks -= numStocks;
//        money += (int) (this.stock.getCurrValue() * numStocks);
//    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (int) (prime * result + ((id == null) ? 0 : id));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (id == null) {
            return other.id == null;
        } else return id.equals(other.id);
    }
}
