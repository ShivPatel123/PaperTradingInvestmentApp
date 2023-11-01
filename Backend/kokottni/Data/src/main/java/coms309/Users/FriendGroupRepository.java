package coms309.Users;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendGroupRepository extends JpaRepository<FriendGroup, Integer> {

    FriendGroup findBygroupName(String groupName);

}