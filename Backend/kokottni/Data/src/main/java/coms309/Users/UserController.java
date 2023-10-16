package coms309.Users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import coms309.Stocks.Stock;
import coms309.Stocks.StockRepository;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    StockRepository stockRepository;

    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";

    @GetMapping(path = "/users")
    List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping(path = "/users/{id}")
    User getUserById(@PathVariable Long id){
        return userRepository.getOne(id);
    }

//    @GetMapping(path = "/purchase/{id}/{numStocks}")
//    User purchaseById(@PathVariable int id, @PathVariable int numStocks){
//        userRepository.findById(id).purchase(numStocks);
//        return userRepository.findById(id);
//    }
//
//    @GetMapping(path = "/sell/{id}/{numStocks}")
//    User sellById(@PathVariable int id, @PathVariable int numStocks){
//        userRepository.findById(id).sellStocks(numStocks);
//        return userRepository.findById(id);
//    }

    @PostMapping(path = "/users")
    String createUser(@RequestBody User user, @PathVariable Long id){
        //if there is no body or the username already exists
        if (user == null)
            return "no user input";
        if (userRepository.getOne(id).getUsername() !=null)
            return "username already taken";
        userRepository.save(user);
        return success;
    }
    @PostMapping("/login")
    String login(@RequestBody LoginAttempt login, @PathVariable Long id){
        User user = userRepository.getOne(id);
        if (user != null) {
            if (user.getPassword().equals(login.getPassword())) {
                return "Success!";
            } else {
                return "Wrong Password";
            }
        } else {
            return "Username not Found";
        }
    }

    @PutMapping("/users/{id}")
    User updateUser(@PathVariable Long id, @RequestBody User request){
        User user = userRepository.getOne(id);
        userRepository.save(request);
        return userRepository.getOne(id);
    }

    @PutMapping("/users/{userId}/stocks/{stockId}")
    String assignStockToUser(@PathVariable Long userId, @PathVariable Long stockId){
        User user = userRepository.getOne(userId);
        Stock laptop = stockRepository.getOne(stockId);
        laptop.setUser(user);
        user.setStock(laptop);
        userRepository.save(user);
        return success;
    }

    @DeleteMapping(path = "/users/{id}")
    String deleteUser(@PathVariable Long id){
        userRepository.getOne(id);
        return success;
    }
}
