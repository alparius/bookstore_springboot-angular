package edu.mpp.bookstore2.core.service;

import edu.mpp.bookstore2.core.model.User;
import edu.mpp.bookstore2.core.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired private UserRepository userRepository;

    @Override
    public User getUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }
}
