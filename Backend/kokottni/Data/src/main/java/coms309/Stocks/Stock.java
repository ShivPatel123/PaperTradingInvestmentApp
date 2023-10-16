package coms309.Stocks;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import coms309.Users.User;

import java.util.Set;

@Entity
@Table(name = "stock")
public class Stock {

    @Id
    @Column(name = "id")
    private Long id;
    private String symbol;
    private String company;
    private double currValue;
    private double prevDayChange;

    @ManyToMany(mappedBy = "stock")
    private Set<User> userOwners;

    @OneToMany(mappedBy = "stock")
    private Set<StockPurchased> amountPurchased;


    public Stock(Long id, String symbol, String company, double currValue, double prevDayChange){
        this.id = id;
        this.symbol = symbol;
        this.company = company;
        this.currValue = currValue;
        this.prevDayChange = prevDayChange;
    }

    public Stock(){}

    public Long getId(){return id;}
    public void setId(Long id){this.id = id;}
    public String getSymbol(){return  symbol;}
    public void setSymbol(String symbol){this.symbol = symbol;}
    public String getCompany(){return company;}
    public void setCompany(String company){this.company = company;}
    public double getCurrValue(){return currValue;}
    public void setCurrValue(double currValue){this.currValue = currValue;}
    public double getPrevDayChange(){return prevDayChange;}
    public void setPrevDayChange(double prevDayChange){this.prevDayChange = prevDayChange;}
    public void setUser(User user){userOwners.add(user);}

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        Stock other = (Stock) obj;
        if (id == null) {
            return other.id == null;
        } else return id.equals(other.id);
    }
}
