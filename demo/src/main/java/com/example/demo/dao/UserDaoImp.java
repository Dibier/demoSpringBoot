/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.dao;

import com.example.demo.model.User;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author dibier
 */
@Repository
@Transactional
public class UserDaoImp implements UserDao {
    
    @PersistenceContext
    EntityManager en;

    @Override
    public List<User> getUsers() {
        String query = "FROM User";
        return en.createQuery(query).getResultList();
    }
    
    @Override
    public void deleteUser(int id) {
        en.remove(en.find(User.class, id));
    }
    
    @Override
    public void saveUser(User u) {
        en.merge(u);
    }
    
    @Override
    public boolean verificarEmailPassword(User u) {
        String query = "FROM User WHERE email = :email AND password = :password";
        List<User> users = en.createQuery(query)
                .setParameter("email", u.getEmail())
                .setParameter("password", u.getPassword())
                .getResultList();
        
        return !users.isEmpty();
    }

    @Override
    public User getUser(int id) {
        return en.find(User.class, id);
    }

}
