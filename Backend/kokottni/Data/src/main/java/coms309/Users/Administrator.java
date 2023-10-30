package coms309.Users;

import javax.persistence.*;

import coms309.Stocks.Stock;
import coms309.Stocks.StockPurchased;
import coms309.Stocks.StockPurchasedRepository;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;

import java.util.*;

@Entity
@Table(name = "admin")
public class Administrator extends User{
    public Administrator(Long id, double money, String name, String email, String dob, String username, String password){
        super(id, money, name, email, dob, username, password);
        this.setPrivilege('a');
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null) return false;
        if(getClass() != obj.getClass()) return false;
        Administrator other = (Administrator) obj;
        if(this.getId() == null){
            return other.getId() == null;
        }else{
            return this.getPrivilege() == other.getPrivilege();
        }
    }
}
