package coms309.Users;

import java.util.List;

import coms309.Stocks.StockPurchased;
import coms309.Stocks.StockPurchasedRepository;
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

    long purchaseNum = 5;
    @Autowired
    UserRepository userRepository;
    @Autowired
    StockRepository stockRepository;

    @Autowired
    StockPurchasedRepository stockPurchasedRepository;

    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";

    @GetMapping(path = "/users")
    List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping(path = "/user/{id}")
    List<StockPurchased> getAllStocksForUser(@PathVariable long id){return userRepository.getOne(id).getStocks();}

    @GetMapping(path = "/users/{id}")
    User getUserById(@PathVariable Long id){
        return userRepository.getOne(id);
    }

    @GetMapping(path = "/buy/{id}/user/{uid}/amt/{amount}")
    StockPurchased purchaseStock(@PathVariable long id, @PathVariable long uid, @PathVariable int amount){
        Stock stock = stockRepository.getOne(id);
        userRepository.getOne(uid).purchase(amount, stock, purchaseNum);
        ++purchaseNum;
        stockPurchasedRepository.save(userRepository.getOne(uid).getStocks().get(userRepository.getOne(uid).getStocks().size() - 1));
        return userRepository.getOne(uid).getStocks().get(userRepository.getOne(uid).getNumStocksPurchased());
    }

    @GetMapping(path = "/sell/{id}/user/{id}/{numStocks}")
    double sellStock(@PathVariable long id, @PathVariable long uid, @PathVariable int numStocks){
        Stock stock = stockRepository.getOne(id);
        int currLength = userRepository.getOne(uid).getStocks().size();
        StockPurchased changed = userRepository.getOne(uid).sell(numStocks, stock);
        if(currLength != userRepository.getOne(uid).getStocks().size()){
            stockPurchasedRepository.delete(changed);
        }else{
            stockPurchasedRepository.delete(changed);
            modifySPRepo(uid);
        }
        return stock.getCurrValue() * numStocks;
    }

    private void modifySPRepo(long uid){
        int foundidx = -1;
        for(long i = 0; i < stockPurchasedRepository.count(); ++i){
            for(int j = 0; j < userRepository.getOne(uid).getStocks().size(); ++j){
                if(!stockPurchasedRepository.getOne(i).getStock().equals(userRepository.getOne(uid).getStocks().get(j).getStock())){
                    foundidx = j;
                    break;
                }
            }
            if(foundidx != -1){
                break;
            }
        }
        stockPurchasedRepository.save(userRepository.getOne(uid).getStocks().get(foundidx));
    }


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

    @PutMapping("/users/{userId}/stocks/{stockId}/{numPurchasing}")
    String assignStockToUser(@PathVariable Long userId, @PathVariable Long stockId, @PathVariable int numPurchasing){
        User user = userRepository.getOne(userId);
        Stock stock = stockRepository.getOne(stockId);
        stock.setUser(user, numPurchasing, userId);
        user.setStock(stock, numPurchasing, stockId);
        userRepository.save(user);
        stockRepository.save(stock);
        return success;
    }

    @DeleteMapping(path = "/users/{id}")
    String deleteUser(@PathVariable Long id){
        userRepository.getOne(id);
        return success;
    }
}
