package com.example.spring.controller;

import com.example.spring.exception.DataAlreadyPresentException;
import com.example.spring.exception.ResourceNotFoundException;
import com.example.spring.exception.UnknownError;
import com.example.spring.model.User;
import com.example.spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;

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
        try {
            return userRepository.save(user);
        }catch (Exception e)
        {
            throw new DataAlreadyPresentException();
        }
    }

    //login
    @PostMapping("/user/auth")
    public User login(@Valid @RequestBody User user) {
        try {
            User userResp =  userRepository.findByNameAndPassword(user.getName(), user.getPassword());
            if(userResp == null)
                throw new Exception();
            else
                return userResp;
        }catch (Exception e)
        {
            throw new ResourceNotFoundException("No data present with given user and pwd");
        }
    }


}
