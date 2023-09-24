package coms309.Users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<User, Long>{
    User findById(int id);
    void deleteById(int id);

    @Transactional
    User findByStockId(int id);

}
