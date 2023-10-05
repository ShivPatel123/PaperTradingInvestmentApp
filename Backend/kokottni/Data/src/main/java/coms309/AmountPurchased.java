package coms309;

import javax.persistence.*;

import coms309.Users.User;
import coms309.Stocks.Stock;

import java.util.Date;

@Entity
public class AmountPurchased {
    @EmbeddedId
    AmountPurchased id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne
    @MapsId("stockId")
    @JoinColumn(name = "stock_id")
    Stock stock;

    int numPurchased;
    int currValue;
    Date datePurchased;

    public AmountPurchased(int numStocks, int currValue, Date date){
        this.numPurchased = numStocks;
        this.currValue = currValue;
        this.datePurchased = date;
    }

    public AmountPurchased(){}
}


