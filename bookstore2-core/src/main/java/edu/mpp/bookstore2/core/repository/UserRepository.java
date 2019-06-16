package edu.mpp.bookstore2.core.repository;

import edu.mpp.bookstore2.core.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MyJpaRepository<User, Long> {

    @Query("select u from User u where u.userName=?1")
    User getUserByUserName(String userName);

    // this is auto created stuff hehe
    User findByUserName(String userName);
}
