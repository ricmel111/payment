package com.allstate.payments.service;

import com.allstate.payments.data.UserRepository;
import com.allstate.payments.domain.User;
import com.allstate.payments.domain.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class BootstrapService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @PostConstruct
    public void createInitialUsers() {
        //if no users, add in the following:
        if (userRepository.count() == 0) {
            User user1 = new User("matt", "Matt", "pass1", UserRole.USER);
            User user2 = new User("sally", "Sally", "pass1", UserRole.MANAGER);
            userService.save(user1);
            userService.save(user2);
        }

    }
}


//IOC: Spring will instantiate the classes. @Service, @Respository, @RestController

//Dependency injection: Spring will call a setter method for us. @Autowired