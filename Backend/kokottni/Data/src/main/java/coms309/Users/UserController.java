package coms309.Users;

import java.util.ArrayList;
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
    List<StockPurchased> getAllStocksForUser(@PathVariable long id){return userRepository.findById(id).getStocks();}

    @GetMapping(path = "/users/{id}")
    User getUserById(@PathVariable long id){
        return userRepository.findById(id);
    }

    @GetMapping(path = "/userByName/{username}")
    User getUserByUsername(@PathVariable String username){return userRepository.findByUsername(username);}

    @GetMapping(path = "/buy/{id}/user/{uid}/amt/{amount}")
    double purchaseStock(@PathVariable long id, @PathVariable long uid, @PathVariable int amount){
        if(userRepository.findById(uid).getPrivilege() == 'b') return -1;
        Stock stock = stockRepository.findById(id);
        int countBefore = userRepository.findById(uid).getStocks().size();
        StockPurchased potentiallyRemove = userRepository.findById(uid).purchase(amount, stock, purchaseNum);
        ++purchaseNum;
        if(countBefore != userRepository.findById(uid).getStocks().size()){
            stockPurchasedRepository.save(userRepository.findById(uid).getStocks().get(userRepository.findById(uid).getStocks().size() - 1));
        }else{
            modifySPRepoPurchase(uid);
            stockPurchasedRepository.delete(potentiallyRemove);
        }
        return stock.getCurrValue() * amount;
    }

    private void modifySPRepoPurchase(long uid){
        int foundidx = -1;
        for(long i = 0; i < stockPurchasedRepository.count(); ++i){
            for(int j = 0; j < userRepository.findById(uid).getStocks().size(); ++j){
                if(stockPurchasedRepository.findById(i).getStock().equals(userRepository.findById(uid).getStocks().get(j).getStock()) && stockPurchasedRepository.findById(i).getUser().equals(userRepository.findById(uid))){
                    foundidx = j;
                    break;
                }
            }
            if(foundidx != -1){
                break;
            }
        }
        stockPurchasedRepository.save(userRepository.findById(uid).getStocks().get(foundidx));
    }


    @GetMapping(path = "/friendgroup")
    List<FriendGroup> getFriendGroups(){
        return friendGroupRepository.findAll();
    }

    //gets list of people in the friend group
    @GetMapping(path = "/friendgroup/{groupName}")
    List<User> getGroupMembers(@PathVariable String groupName){
        return friendGroupRepository.findBygroupName(groupName).getGroupMembers();
    }


    @GetMapping(path = "/sell/{id}/user/{uid}/{numStocks}")
    double sellStock(@PathVariable long id, @PathVariable long uid, @PathVariable int numStocks){
        Stock stock = stockRepository.findById(id);
        if(userRepository.findById(uid).getPrivilege() == 'b' || userRepository.findById(uid).getStocks().contains(stock)) return -1;
        int currLength = userRepository.findById(uid).getStocks().size();
        StockPurchased changed = userRepository.findById(uid).sell(numStocks, stock);
        int soldStocks = changed.getNumPurchased();
        if(currLength != userRepository.findById(uid).getStocks().size()){
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
            for(int j = 0; j < userRepository.findById(uid).getStocks().size() && stockPurchasedRepository.findById(i).getUser().getId().equals(uid) && stockPurchasedRepository.findById(i).getStock().equals(userRepository.findById(uid).getStocks().get(j).getStock()); ++j){
                if(stockPurchasedRepository.findById(i).getNumPurchased() != userRepository.findById(uid).getStocks().get(j).getNumPurchased()){
                    foundidx = j;
                    break;
                }
            }
            if(foundidx != -1){
                break;
            }
        }
        if(foundidx != -1){
            return stockPurchasedRepository.save(userRepository.findById(uid).getStocks().get(foundidx));
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
        if(friendGroupRepository.findBygroupName(groupName) == null) {
            FriendGroup friendGroup = new FriendGroup();
            friendGroup.setGroupName(groupName);
            friendGroupRepository.save(friendGroup);
            return success;
        }
        return failure;
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

    @ApiOperation(value = "Add User {userId} to friend group {groupName}", response = String, tags = "/friendgroup/{groupName}/{userID}")

    @PutMapping("/friendgroup/{groupName}/{userID}")
    @Transactional
    String addUserToGroup(@PathVariable String groupName, @PathVariable int userID) {
        FriendGroup group = friendGroupRepository.findBygroupName(groupName);
        User user = userRepository.findById(userID);

        if (group != null && user != null) {
            user.setFriendGroup(group);
            userRepository.save(user);

            // Save the friend group to update the user-group relationship
            friendGroupRepository.save(group);

            return success;
        } else {
            return failure;
        }
    }



    @PutMapping("/users/{id}")
    User updateUser(@PathVariable long id, @RequestBody User request){
        User user = userRepository.findById(id);
        if(user.getPrivilege() == 'b') return null;
        userRepository.save(request);
        return userRepository.findById(id);
    }

    @PutMapping("/users/{userId}/stocks/{stockId}/{numPurchasing}")
    String assignStockToUser(@PathVariable long userId, @PathVariable long stockId, @PathVariable int numPurchasing){
        User user = userRepository.findById(userId);
        if(user.getPrivilege() == 'b') return failure;
        Stock stock = stockRepository.findById(stockId);
        stock.setUser(user, numPurchasing, userId);
        user.setStock(stock, numPurchasing, stockId);
        userRepository.save(user);
        stockRepository.save(stock);
        return success;
    }

    @DeleteMapping(path = "/users/{id}")
    String deleteUser(@PathVariable Long id){
        userRepository.findById(id);
        return success;
    }


    //removes user userID from FriendGroup groupName

    @ApiOperation(value = "Remove User {userId} from friend group {groupName}", response = String, tags = "/friendgroup/{groupName}/{userID}")

    @DeleteMapping("/friendgroup/{groupName}/{userID}")
    String removeUserFromGroup(@PathVariable String groupName, @PathVariable int userID){
        friendGroupRepository.findBygroupName(groupName).removeUser(userRepository.findById(userID));
        return success;
    }
    @PostMapping(path = "/newstocks/{uid}")
    String createStock(@RequestBody Stock stock, @PathVariable long uid){
        User user = userRepository.findById(uid);
        if(user.getPrivilege() != 'a' || stock == null) return failure;
        stock.setId(stockNum);
        ++stockNum;
        stockRepository.save(stock);
        return success;
    }

    @DeleteMapping(path = "/stocks/{sid}/{uid}")
    String deleteStock(@PathVariable long sid, @PathVariable long uid){
        User user = userRepository.findById(uid);
        if(user.getPrivilege() != 'a') return failure;
        stockRepository.deleteById(sid);
        return success;
    }

    @PutMapping(path = "/update/{uid}")
    String updateAllStocks(@PathVariable long uid){
        if(userRepository.findById(uid).getPrivilege() != 'a') return failure;
        stockAPI.updateAllStocks(stockRepository);
        return success;
    }

    @PutMapping(path = "/banuser/{uid}/byadmin/{aid}")
    String banUser(@PathVariable long uid, @PathVariable long aid){
        User admin = userRepository.findById(aid);
        if(admin.getPrivilege() != 'a') return failure;
        User user = userRepository.findById(uid);
        user.setPrivilege('b');
        removeStocks(uid);
        userRepository.save(user);
        return success;
    }

    private void removeStocks(long uid){
        for(long i = 1; i < stockPurchasedRepository.count(); ++i){
            for(int j = 0; j < userRepository.findById(uid).getStocks().size(); ++j){
                if(stockPurchasedRepository.findById(i).getUser().getId().equals(userRepository.findById(uid).getId())){
                    stockPurchasedRepository.deleteById(i);
                    userRepository.findById(uid).getStocks().remove(j);
                    break;
                }
            }
        }
    }

    @PutMapping(path = "/unban/{uid}/byadmin/{aid}")
    String unbanUser(@PathVariable long uid, @PathVariable long aid){
        User admin = userRepository.findById(aid);
        if(admin.getPrivilege() != 'a') return failure;
        User user = userRepository.findById(uid);
        user.setPrivilege('u');
        userRepository.save(user);
        return success;
    }

}
