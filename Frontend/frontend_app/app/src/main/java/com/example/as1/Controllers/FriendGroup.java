package com.example.as1.Controllers;

import java.util.ArrayList;
import java.util.List;

public class FriendGroup {
        private int id;
        private String groupName;
        private List<User> groupMembers;
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

    }

