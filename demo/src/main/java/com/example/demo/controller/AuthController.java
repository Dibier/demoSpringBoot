/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.controller;

import com.example.demo.dao.UserDao;
import com.example.demo.model.User;
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
    
    @RequestMapping(value = "api/login", method = RequestMethod.POST)
    public String login(@RequestBody User u) {
        if (userDao.verificarEmailPassword(u)) {
            return "Ok";
        } else {
            return "Fail";
        }
    }
}
