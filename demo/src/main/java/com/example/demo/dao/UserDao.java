/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.demo.dao;

import com.example.demo.model.User;
import java.util.List;

/**
 *
 * @author dibier
 */
public interface UserDao {
    public List<User> getUsers();
    public void deleteUser(int id);
    public void saveUser(User u);
    public User getUserByEmailAndPassword(User u);
    public User getUser(int id);
}
