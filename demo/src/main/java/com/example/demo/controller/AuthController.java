/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.controller;

import com.example.demo.dao.UserDao;
import com.example.demo.model.User;
import com.example.demo.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author dibier
 */

@RestController
public class AuthController {
    @Autowired
    private UserDao userDao;
    
    @Autowired
    private JWTUtil jwtUtil;
    
    @RequestMapping(value = "api/login", method = RequestMethod.POST)
    public String login(@RequestBody User u) {
        
        if (userDao.getUserByEmailAndPassword(u) != null) {
            
            String token = jwtUtil.create(String.valueOf(u.getId()), u.getEmail());
            return token;
            
        } else {
            return "Fail";
        }
    }
}
