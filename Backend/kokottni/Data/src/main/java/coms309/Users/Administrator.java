package coms309.Users;

import javax.persistence.*;

import coms309.Stocks.Stock;
import coms309.Stocks.StockPurchased;
import coms309.Stocks.StockPurchasedRepository;

import java.util.*;

@Entity
@Table(name = "admin")
public class Administrator extends User{

}
