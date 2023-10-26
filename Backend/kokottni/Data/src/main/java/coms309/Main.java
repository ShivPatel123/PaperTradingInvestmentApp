package coms309;

import coms309.Stocks.StockPurchasedRepository;
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
    CommandLineRunner initUser(UserRepository userRepository, StockRepository stockRepository, StockPurchasedRepository spRepository){
        return args -> {

            User user1 = new User(1L, 1987654, "Nick", "kokottni@iastate.edu", "oldenough", "user", "Password");
            User user2 = new User(2L, 34578, "Josh", "jwhit@iastate.edu", "oldenough", "username", "password");
            User user3 = new User(3L, 6543, "Shiv", "shiv@iastate.edu", "nah", "username2", "password");
            User user4 = new User(4L, 7765, "Skyler", "sky@iastate.edu", "yup", "user3", "passman");
            Stock stock1 = new Stock(1L, "TSLA", "Tesla", 101.23, -2.13);
            Stock stock2 = new Stock(2L, "INTL", "Intel", 67.12, 1.34);
            Stock stock3 = new Stock(3L, "GGL", "Google", 213.56, 3.14);
            Stock stock4 = new Stock(4L, "EXAS", "Exact Sciences", 67.34, -2.13);

            userRepository.save(user1);
            userRepository.save(user2);
            userRepository.save(user3);
            userRepository.save(user4);
            stockRepository.save(stock1);
            stockRepository.save(stock2);
            stockRepository.save(stock3);
            stockRepository.save(stock4);

            user1.setStock(stock1, 2, stock1.getId());
            stock1.setUser(user1, 2, stock1.getId());
            user2.setStock(stock2, 3, stock2.getId());
            stock2.setUser(user2, 3, stock2.getId());
            user3.setStock(stock3, 1, stock3.getId());
            stock3.setUser(user3, 1, stock3.getId());
            user4.setStock(stock4, 4, stock4.getId());
            stock4.setUser(user4, 4, stock4.getId());
            spRepository.save(user1.getStocks().get(0));
            spRepository.save(user2.getStocks().get(0));
            spRepository.save(user3.getStocks().get(0));
            spRepository.save(user4.getStocks().get(0));
        };
    }
}