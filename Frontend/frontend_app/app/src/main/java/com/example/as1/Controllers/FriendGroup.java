package com.example.as1.Controllers;

import java.util.ArrayList;
import java.util.List;

/**
 * The group object class
 *  Mirrors FriendGroup class on backend
 *
 */
public class FriendGroup {
<<<<<<< HEAD

    /**
     * Group ID
     */
    protected int id;
    /**
     * Group name
     */
    protected String groupName;
    /**
     * List of Users that have been added to the group by an admin/group leader
     */
    protected List<User> groupMembers;

    /**
     * Getter for group name
     * @return  String groupName
     */
    public String getGroupName(){
=======
        private int id;
        private String groupName;

        private List<User> groupMembers;
        private long groupLeaderID;


        public String getGroupName(){
>>>>>>> 52489c237ccaf33b89db9472694445c088e5935c
            return groupName;
        }
    public int getId() {
        return id;
    }

<<<<<<< HEAD

    /**
     * Group constructor to create a new instance of a group. Can be sent back to server to create a new group.
     * @param id Group ID
     * @param groupName Group Name
     * @param groupMembers List of Users that are group members
     */
    public FriendGroup(int id, String groupName, List<User> groupMembers) {
        this.id = id;
        this.groupName = groupName;
        this.groupMembers = groupMembers;
    }

    /**
     * Getter for list of Users in a FriendGroup's groupMembers list
     * @return List<String> of user names
     */
    public List<String> getGroupMembers() {
            List<String> groupMembersNames = new ArrayList<>();
            for(User user : groupMembers){
                groupMembersNames.add(user.getName());
            }
=======
    public void setId(int id) {
        this.id = id;
    }
>>>>>>> 52489c237ccaf33b89db9472694445c088e5935c

    public void setGroupMembers(List<User> groupMembers) {
        this.groupMembers = groupMembers;
    }

    public long getGroupLeaderID() {
        return groupLeaderID;
    }

    public void setGroupLeaderID(long groupLeaderID) {
        this.groupLeaderID = groupLeaderID;
    }

        public List<User> getGroupMembers() {
           return groupMembers;
        }

    /**
     * Setter for group name
     * @param groupName string that is the name of the group
     */
    public void setGroupName(String groupName){
            this.groupName = groupName;
        }

    /**
     * Setter that adds a user to the group
     * @param user User object that will be added to the group the method is called on
     */
    public void addUser(User user){
            groupMembers.add(user);
        }

    /**
     * Setter that removes a user from the group
     * @param user User object that will be removed from the group the method is called on
     */
    public void removeUser(User user){
            groupMembers.remove(user);
        }

    }

