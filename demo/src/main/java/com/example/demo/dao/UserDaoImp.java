/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.dao;

import com.example.demo.model.User;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
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
    public User getUserByEmailAndPassword(User u) {
        String query = "FROM User WHERE email = :email";
        List<User> users = en.createQuery(query)
                .setParameter("email", u.getEmail())
                .getResultList();
        if (users.isEmpty()) {
            return null;
        }
        String passwordHashed = users.get(0).getPassword();
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        if(argon2.verify(passwordHashed, u.getPassword())) {
            return u;
        }
        return null;
    }

    @Override
    public User getUser(int id) {
        return en.find(User.class, id);
    }

}
