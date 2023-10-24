package coms309.Users;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;


@Entity
public class FriendGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String groupName;

    @OneToMany(mappedBy = "friendGroup")
    private List<User> groupMembers;
    //private MessageRepository MessageRepository;
    public String getGroupName(){
        return groupName;
    }
    public void setGroupName(String groupName){
        this.groupName = groupName;
    }

    public void addUser(User user){
        groupMembers.add(user);
    }

    public void removeUser(User user){
        groupMembers.remove(user);
    }

    //TODO
    //getAllMessages(){
    //return message log from repo
    //}

}