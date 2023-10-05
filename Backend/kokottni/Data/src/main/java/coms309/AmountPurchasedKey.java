package coms309;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class AmountPurchasedKey implements Serializable {
    @Column(name = "user_id")
    Long userId;

    @Column(name = "stock_id")
    Long stockId;

    public AmountPurchasedKey(Long userId, Long stockId){
        this.userId = userId;
        this.stockId = stockId;
    }

    public Long getUserId(){return this.userId;}

    public void setUserId(Long userId){this.userId = userId;}

    public Long getStockId(){return this.stockId;}

    public void setStockId(Long stockId){this.stockId = stockId;}

    @Override
    public boolean equals(Object obj) {
        if(this == obj){return true;}
        if(obj == null){return false;}
        if(this.getClass() != obj.getClass()){return false;}
        AmountPurchasedKey key = (AmountPurchasedKey) obj;
        return userId.equals(key.userId) && stockId.equals(key.stockId);
    }

    @Override
    public int hashCode(){
        int hash = 5;
        return (userId.hashCode() - hash) * (stockId.hashCode() + hash);
    }
}
