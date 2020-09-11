package com.example.spring.controller;

import com.example.spring.model.User;
import com.example.spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityListeners;
import javax.validation.Valid;

@RestController
@RequestMapping("/app")
@EntityListeners(AuditingEntityListener.class)
public class UserController {

    @Autowired
    UserRepository userRepository;

    //register
    @PostMapping("/user")
    public User register(@Valid @RequestBody User user) {
        return userRepository.save(user);
    }

    //login
    @PostMapping("/user/auth")
    public User login(@Valid @RequestBody User user) {
        return userRepository.findByNameAndPassword(user.getName(), user.getPassword());
    }


}
