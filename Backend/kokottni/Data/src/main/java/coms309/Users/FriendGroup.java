package coms309.Users;

import java.util.List;
import java.util.ArrayList;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
public class FriendGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String groupName;




    @Column
    @OneToMany(mappedBy = "friendGroup")
    private List<User> groupMembers;


    //private MessageRepository MessageRepository;
    public String getGroupName(){
        return groupName;
    }

    public List<String> getGroupMembers() {
        List<String> groupMembersNames = new ArrayList<>();
        for(User user : groupMembers){
            groupMembersNames.add(user.getName());
        }

        return groupMembersNames;
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