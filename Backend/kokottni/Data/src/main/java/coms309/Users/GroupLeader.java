package coms309.Users;

import javax.persistence.*;

import coms309.Stocks.Stock;
import coms309.Stocks.StockPurchased;
import coms309.Stocks.StockPurchasedRepository;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;

import java.util.*;

@Entity
@Table(name = "Group Leader")
public class GroupLeader extends User {
    public GroupLeader(Long id, double money, String name, String email, String dob, String username, String password) {
        super(id, money, name, email, dob, username, password);
        this.setPrivilege('g');
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null) return false;
        if(getClass() != obj.getClass()) return false;
        GroupLeader gl = (GroupLeader) obj;
        if(this.getId() == null){
            return gl.getId() == null;
        }else{
            return this.getPrivilege() == gl.getPrivilege();
        }
    }
}