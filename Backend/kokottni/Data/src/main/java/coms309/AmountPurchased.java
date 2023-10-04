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
    @MapsId("user_id")
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne
    @MapsId("stock_id")
    @JoinColumn(name = "stock_id")
    Stock stock;

    int numPurchased;
    Date datePurchased;

    public AmountPurchased(int numStocks, Date date){
        this.numPurchased = numStocks;
        this.datePurchased = date;
    }

    public AmountPurchased(){}
}


