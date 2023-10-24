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

    @Autowired
    FriendGroupRepository friendGroupRepository;

    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";

    @GetMapping(path = "/users")
    List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping(path = "/users/{id}")
    User getUserById( @PathVariable int id){
        return userRepository.findById(id);
    }

    @GetMapping(path = "/purchase/{id}/{numStocks}")
    User purchaseById(@PathVariable int id, @PathVariable int numStocks){
        userRepository.findById(id).purchase(numStocks);
        return userRepository.findById(id);
    }

    @GetMapping(path = "/sell/{id}/{numStocks}")
    User sellById(@PathVariable int id, @PathVariable int numStocks){
        userRepository.findById(id).sellStocks(numStocks);
        return userRepository.findById(id);
    }

    @PostMapping(path = "/users")
    String createUser(@RequestBody User user){
        //if there is no body or the username already exists
        if (user == null)
            return "no user input";
        if (userRepository.findByUsername(user.getUsername())!=null)
            return "username already taken";
        userRepository.save(user);
        return success;
    }
    @PostMapping("/login")
    String login(@RequestBody LoginAttempt login){
        User user = userRepository.findByUsername(login.getUsername());
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
    User updateUser(@PathVariable int id, @RequestBody User request){
        User user = userRepository.findById(id);
        if(user == null)
            return null;
        userRepository.save(request);
        return userRepository.findById(id);
    }

    @PutMapping("/users/{userId}/stocks/{stockId}")
    String assignStockToUser(@PathVariable int userId,@PathVariable int stockId){
        User user = userRepository.findById(userId);
        Stock laptop = stockRepository.findById(stockId);
        if(user == null || laptop == null)
            return failure;
        laptop.setUser(user);
        user.setStock(laptop);
        userRepository.save(user);
        return success;
    }

    @DeleteMapping(path = "/users/{id}")
    String deleteUser(@PathVariable int id){
        userRepository.deleteById(id);
        return success;
    }

    //removes user userID from FriendGroup groupName
    @DeleteMapping("/friendgroup/{groupName}/{userID}")
    String removeUserFromGroup(@PathVariable String groupName, @PathVariable int userID){
        friendGroupRepository.findBygroupName(groupName).removeUser(userRepository.findById(userID));
        return success;
    }
}
