package edu.mpp.bookstore2.web.controller;

import edu.mpp.bookstore2.core.model.User;
import edu.mpp.bookstore2.core.service.UserService;
import edu.mpp.bookstore2.web.converter.UserConverter;
import edu.mpp.bookstore2.web.dto.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired private UserService service;
    @Autowired private UserConverter converter;

//    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
//    UserDto getUser(@PathVariable String username) {
//        log.trace("getUser: username={}", username);
//
//        User userReceived = this.service.getUserByUserName(username);
//        UserDto result = this.converter.convertModelToDto(userReceived);
//
//        log.trace("getUser: username={}", username);
//        return result;
//    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    UserDto getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        System.out.println("------------ " + username);
        User userReceived = this.service.getUserByUserName(username);
        UserDto result = this.converter.convertModelToDto(userReceived);

        log.trace("------------ getUser: {}", result);
        return result;
    }
}
