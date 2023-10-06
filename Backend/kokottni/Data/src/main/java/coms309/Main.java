package coms309;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import coms309.Stocks.Stock;
import coms309.Stocks.StockRepository;
import coms309.Users.User;
import coms309.Users.UserRepository;



// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
@SpringBootApplication
@EnableJpaRepositories
public class Main {
    public static void main(String[] args) {SpringApplication.run(Main.class, args);}
    @Bean
    CommandLineRunner initUser(UserRepository userRepository, StockRepository stockRepository){
        return args -> {
            User user1 = new User(1, 1987654, "Nick", "kokottni@iastate.edu", "oldenough");
            User user2 = new User(2, 34578, "Josh", "jwhit@iastate.edu", "oldenough");
            User user3 = new User(3, 6543, "Shiv", "shiv@iastate.edu", "nah");
            User user4 = new User(4, 7765, "Skyler", "sky@iastate.edu", "yup");
            Stock stock1 = new Stock(1, "TSLA", "Tesla", 101.23, -2.13);
            Stock stock2 = new Stock(2, "INTL", "Intel", 67.12, 1.34);
            Stock stock3 = new Stock(3, "GGL", "Google", 213.56, 3.14);
            Stock stock4 = new Stock(4, "EXAS", "Exact Sciences", 67.34, -2.13);
            user1.setStock(stock1);
            user2.setStock(stock2);
            user3.setStock(stock3);
            user4.setStock(stock4);
            userRepository.save(user1);
            userRepository.save(user2);
            userRepository.save(user3);
            stockRepository.save(stock1);
            stockRepository.save(stock2);
            stockRepository.save(stock3);
        };
    }
}