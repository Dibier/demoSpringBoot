/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author dibier
 */
@Entity
@Table(name = "usuarios")
@ToString @EqualsAndHashCode
public class User {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Getter @Setter @Column(name = "id")
    private Integer id;
    
    @Getter @Setter @Column(name = "nombre")
    private String nombre;
    
    @Getter @Setter @Column(name = "apellido")
    private String apellido;
    
    @Getter @Setter @Column(name = "telefono")
    private String telefono;
    
    @Getter @Setter @Column(name = "email")
    private String email;
    
    @Getter @Setter @Column(name = "password")
    private String password;
    
   /* public User(
            String nombre,
            String apellido, 
            String telefono, 
            String email,
            String password) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.password = password;
    }*/
}
