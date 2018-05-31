package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taotao.dao.entity.User;
import com.taotao.service.UserService;

@RestController
public class UserController
{
    @Autowired
    private UserService userService;
    
    @PostMapping
    public void addUser() {
        
    }
 
    @GetMapping("/{id}")
    public User getUser(@PathVariable(name="id") int id) {
        User user = userService.getUser(id);
        return user;
    }
}
