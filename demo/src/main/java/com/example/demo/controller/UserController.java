/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.controller;

import com.example.demo.dao.UserDao;
import com.example.demo.model.User;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.security.crypto.argon2;

/**
 *
 * @author dibier
 */
@RestController
public class UserController {
    @Autowired
    private UserDao user;
    
    @RequestMapping(value = "api/users", method = RequestMethod.GET)
    public List<User> getUsers() {
        return user.getUsers();
    }
    
    @RequestMapping(value = "api/delete/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable int id) {
        user.deleteUser(id);
    }
    
    @RequestMapping(value = "api/save", method = RequestMethod.POST)
    public void saveUser(@RequestBody User u) {
        user.saveUser(u);
    }
    
    @RequestMapping(value = "api/save/{id}", method = RequestMethod.PUT)
    public void updateUser(@RequestBody User u, @PathVariable int id) {
        u.setId(id);
        user.saveUser(u);
    }
    
    @RequestMapping(value = "api/user/{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable int id) {
        return user.getUser(id);
    }
    
    
}
