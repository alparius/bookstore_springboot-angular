package edu.mpp.bookstore2.core.service;

import edu.mpp.bookstore2.core.model.User;

public interface UserService {

    User getUserByUserName(String userName);

    void addUser(User user);
}
