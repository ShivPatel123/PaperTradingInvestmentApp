package coms309.Users;

import java.util.List;
import java.util.Objects;

import coms309.Stocks.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    long purchaseNum = 5;
    long stockNum = 5;
    @Autowired
    UserRepository userRepository;
    @Autowired
    StockRepository stockRepository;


    @Autowired
    FriendGroupRepository friendGroupRepository;

    StockUpdater stockAPI = new StockUpdater();

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
        if(userRepository.getOne(uid).getPrivilege() == 'b') return null;
        Stock stock = stockRepository.getOne(id);
        int countBefore = userRepository.getOne(uid).getStocks().size();
        StockPurchased potentiallyRemove = userRepository.getOne(uid).purchase(amount, stock, purchaseNum);
        ++purchaseNum;
        if(countBefore != userRepository.getOne(uid).getStocks().size()){
            stockPurchasedRepository.save(userRepository.getOne(uid).getStocks().get(userRepository.getOne(uid).getStocks().size() - 1));
        }else{
            modifySPRepoPurchase(uid);
            stockPurchasedRepository.delete(potentiallyRemove);
        }
        return userRepository.getOne(uid).getStocks().get(userRepository.getOne(uid).getNumStocksPurchased());
    }

    private void modifySPRepoPurchase(long uid){
        int foundidx = -1;
        for(long i = 0; i < stockPurchasedRepository.count(); ++i){
            for(int j = 0; j < userRepository.getOne(uid).getStocks().size(); ++j){
                if(stockPurchasedRepository.getOne(i).getStock().equals(userRepository.getOne(uid).getStocks().get(j).getStock()) && stockPurchasedRepository.getOne(i).getUser().equals(userRepository.getOne(uid))){
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


    //gets list of people in the friend group
    @GetMapping(path = "/friendgroup/{groupName}")
    List<String> getGroupMembers(@PathVariable String groupName){
        return friendGroupRepository.findBygroupName(groupName).getGroupMembers();
    }


    @GetMapping(path = "/sell/{id}/user/{uid}/{numStocks}")
    double sellStock(@PathVariable long id, @PathVariable long uid, @PathVariable int numStocks){
        if(userRepository.getOne(uid).getPrivilege() == 'b') return -1;
        Stock stock = stockRepository.getOne(id);
        int currLength = userRepository.getOne(uid).getStocks().size();
        StockPurchased changed = userRepository.getOne(uid).sell(numStocks, stock);
        int soldStocks = changed.getNumPurchased();
        if(currLength != userRepository.getOne(uid).getStocks().size()){
            stockPurchasedRepository.delete(changed);
        }else{
            StockPurchased sp = modifySPRepoSell(uid);
            soldStocks -= sp.getNumPurchased();
            stockPurchasedRepository.delete(changed);
        }
        return stock.getCurrValue() * soldStocks;
    }

    private StockPurchased modifySPRepoSell(long uid){
        int foundidx = -1;
        for(long i = 0; i < stockPurchasedRepository.count(); ++i){
            for(int j = 0; j < userRepository.getOne(uid).getStocks().size() && stockPurchasedRepository.getOne(i).getUser().getId().equals(uid) && stockPurchasedRepository.getOne(i).getStock().equals(userRepository.getOne(uid).getStocks().get(j).getStock()); ++j){
                if(stockPurchasedRepository.getOne(i).getNumPurchased() != userRepository.getOne(uid).getStocks().get(j).getNumPurchased()){
                    foundidx = j;
                    break;
                }
            }
            if(foundidx != -1){
                break;
            }
        }
        if(foundidx != -1){
            return stockPurchasedRepository.save(userRepository.getOne(uid).getStocks().get(foundidx));
        }
        return null;
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
    LoginAttempt login(@RequestBody LoginAttempt login){
        User user = userRepository.findByUsername(login.getUsername());
        if(user.getPrivilege() == 'b') return null;
        if (user != null) {
            if (user.getPassword().equals(login.getPassword())) {
                login.setSuccess("Success!");
                return login;
            } else {
                login.setSuccess("Wrong Password");
                return login;
            }
        } else {
            login.setSuccess("Username Not Found");
            return login;
        }
    }
    //creates a new friend group using the name in the requestbody
    @PostMapping(path = "/friendgroup/{groupName}")
    String createFriendGroup(@PathVariable String groupName){
        FriendGroup friendGroup = new FriendGroup();
        friendGroup.setGroupName(groupName);
        friendGroupRepository.save(friendGroup);
        return success;
    }
//
//    //creates a new friend group using the name in the requestbody and adds the user from the path variable into the group
//    @PostMapping(path = "/friendgroup/{userID}")
//    String createFriendGroup(@RequestBody String name, @PathVariable int userID){
//        FriendGroup friendGroup = new FriendGroup();
//        friendGroup.setGroupName(name);
//        friendGroup.addUser(userRepository.findById(userID));
//        friendGroupRepository.save(friendGroup);
//        return success;
//    }

    //adds user userID to FriendGroup groupName
    @PutMapping("/friendgroup/{groupName}/{userID}")
    String addUserToGroup(@PathVariable String groupName, @PathVariable int userID){
        friendGroupRepository.findBygroupName(groupName).addUser(userRepository.findById(userID));
        return success;
    }

    @PutMapping("/users/{id}")
    User updateUser(@PathVariable Long id, @RequestBody User request){
        User user = userRepository.getOne(id);
        if(user.getPrivilege() == 'b') return null;
        userRepository.save(request);
        return userRepository.getOne(id);
    }

    @PutMapping("/users/{userId}/stocks/{stockId}/{numPurchasing}")
    String assignStockToUser(@PathVariable Long userId, @PathVariable Long stockId, @PathVariable int numPurchasing){
        User user = userRepository.getOne(userId);
        if(user.getPrivilege() == 'b') return failure;
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


    //removes user userID from FriendGroup groupName
    @DeleteMapping("/friendgroup/{groupName}/{userID}")
    String removeUserFromGroup(@PathVariable String groupName, @PathVariable int userID){
        friendGroupRepository.findBygroupName(groupName).removeUser(userRepository.findById(userID));
        return success;
    }
    @GetMapping(path = "/stocks/{uid}/{symbol}/{company}/{currValue}/{prevDayChange}")
    String createStock(@PathVariable long uid, @PathVariable String symbol, @PathVariable String company, @PathVariable double currValue, @PathVariable double prevDayChange){
        User user = userRepository.getOne(uid);
        Stock stock = new Stock(stockNum, symbol, company, currValue, prevDayChange);
        if(user.getPrivilege() != 'a' || stock == null) return failure;
        ++stockNum;
        stockRepository.save(stock);
        return success;
    }

    @DeleteMapping(path = "/stocks/{sid}/{uid}")
    String deleteStock(@PathVariable long sid, @PathVariable long uid){
        User user = userRepository.getOne(uid);
        if(user.getPrivilege() != 'a') return failure;
        stockRepository.deleteById(sid);
        return success;
    }

    @PutMapping(path = "/update/{uid}")
    String updateAllStocks(@PathVariable long uid){
        if(userRepository.getOne(uid).getPrivilege() != 'a') return failure;
        stockAPI.updateAllStocks(stockRepository);
        return success;
    }

    @PutMapping(path = "/banuser/{uid}/byadmin/{aid}")
    String banUser(@PathVariable long uid, @PathVariable long aid){
        User admin = userRepository.getOne(aid);
        if(admin.getPrivilege() != 'a') return failure;
        User user = userRepository.getOne(uid);
        user.setPrivilege('b');
        removeStocks(uid);
        userRepository.save(user);
        return success;
    }

    private void removeStocks(long uid){
        for(long i = 1; i < stockPurchasedRepository.count(); ++i){
            for(int j = 0; j < userRepository.getOne(uid).getStocks().size(); ++j){
                if(stockPurchasedRepository.getOne(i).getUser().getId().equals(userRepository.getOne(uid).getId())){
                    stockPurchasedRepository.deleteById(i);
                    userRepository.getOne(uid).getStocks().remove(j);
                    break;
                }
            }
        }
    }

    @PutMapping(path = "/unban/{uid}/byadmin/{aid}")
    String unbanUser(@PathVariable long uid, @PathVariable long aid){
        User admin = userRepository.getOne(aid);
        if(admin.getPrivilege() != 'a') return failure;
        User user = userRepository.getOne(uid);
        user.setPrivilege('u');
        userRepository.save(user);
        return success;
    }

}
