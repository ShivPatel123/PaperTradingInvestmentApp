package coms309.Users;

import java.util.List;
import java.util.ArrayList;
import javax.persistence.*;

import net.bytebuddy.implementation.bind.annotation.IgnoreForBinding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
@Table(name = "friendgroup")
public class FriendGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String groupName;

    @Column(name = "groupLeaderID")
    private long groupLeaderID;




    @OneToMany(cascade = CascadeType.ALL, mappedBy = "friendGroup" )
    private List<User> groupMembers;


    public FriendGroup(int id, String groupName, long groupLeaderID){
        this.id = id;
        this.groupName = groupName;
        this.groupLeaderID = groupLeaderID;
    }

    public FriendGroup() {
        this.id = 0;
        this.groupName = "defaultGroup";
        this.groupLeaderID = 0;
    }

    public String getGroupName(){
        return groupName;
    }

    public List<User> getGroupMembers() {
//        List<String> groupMembersNames = new ArrayList<>();
//        for(User user : groupMembers){
//            groupMembersNames.add(user.getName());
//        }
//
//        return groupMembersNames;
        return groupMembers;
    }


    public void setGroupName(String groupName){
        this.groupName = groupName;
    }

    public void setGroupLeader(User user){this.groupLeaderID = user.getId();}

    public long getGroupLeader(){return groupLeaderID;}

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

    public boolean findUser(User user){return groupMembers.contains(user);}

}