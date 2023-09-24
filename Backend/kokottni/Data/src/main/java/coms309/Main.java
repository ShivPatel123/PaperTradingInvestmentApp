package coms309;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import coms309.Stocks.Stock;
import coms309.Stocks.StockRepository;
import coms309.Users.User;
import coms309.Users.UserRepository;



// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
@SpringBootApplication
public class Main {
    public static void main(String[] args) {SpringApplication.run(Main.class, args);}
    @Bean
    CommandLineRunner initUser(UserRepository userRepository, StockRepository stockRepository){
        return args -> {
            User user1 = new User(1, "Nick", "kokottni@iastate.edu", "oldenough");
            User user2 = new User(2, "Josh", "jwhit@iastate.edu", "oldenough");
            User user3 = new User(3, "Shiv", "shiv@iastate.edu", "nah");
            Stock stock1 = new Stock(1, "TSLA", "Tesla", 101.23, -2.13);
            Stock stock2 = new Stock(2, "INTL", "Intel", 67.12, 1.34);
            Stock stock3 = new Stock(3, "GGL", "Google", 213.56, 3.14);
            user1.setStock(stock1);
            user2.setStock(stock2);
            user3.setStock(stock3);
            stockRepository.save(stock1);
            stockRepository.save(stock2);
            stockRepository.save(stock3);
        };
    }
}