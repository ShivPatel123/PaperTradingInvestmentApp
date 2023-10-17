package coms309.Stocks;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class StockPurchasedKey implements Serializable{

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "stock_id")
    private Long stockId;

    public StockPurchasedKey(){}

    public Long getUserId(){return userId;}

    public Long getStockId(){return stockId;}

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((stockId == null) ? 0 : stockId.hashCode());
        result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
        StockPurchasedKey other = (StockPurchasedKey) obj;
        if (stockId == null) {
            if (other.stockId != null)
                return false;
        } else if (!stockId.equals(other.stockId))
            return false;
        if (userId == null) {
            return other.userId == null;
        } else return userId.equals(other.userId);
    }

}
