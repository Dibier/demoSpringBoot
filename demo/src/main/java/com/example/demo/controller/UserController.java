/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.controller;

import com.example.demo.dao.UserDao;
import com.example.demo.model.User;
import com.example.demo.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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
    
    @Autowired
    private JWTUtil jwt;
    
    private boolean tokenValido(String token) {
        String userId = jwt.getKey(token);
        return userId != null;
    }
    
    @RequestMapping(value = "api/users", method = RequestMethod.GET)
    public List<User> getUsers(@RequestHeader(value = "Authorization") String token) {
       
        if (!tokenValido(token)) {
            return null;
        }
        return user.getUsers();
    }
    
    @RequestMapping(value = "api/delete/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@RequestHeader(value = "Authorization") String token, @PathVariable int id) {
        if (!tokenValido(token)) return;
        user.deleteUser(id);
    }
    
    @RequestMapping(value = "api/save", method = RequestMethod.POST)
    public void saveUser(@RequestBody User u) {
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1, 1024, 1, u.getPassword());
        u.setPassword(hash);
        user.saveUser(u);
    }
    
    @RequestMapping(value = "api/save/{id}", method = RequestMethod.PUT)
    public void updateUser(@RequestBody User u, @PathVariable int id) {
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1, 1024, 1, u.getPassword());
        u.setPassword(hash);
        u.setId(id);
        user.saveUser(u);
    }
    
    @RequestMapping(value = "api/user/{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable int id) {
        return user.getUser(id);
    }
    
    
}
