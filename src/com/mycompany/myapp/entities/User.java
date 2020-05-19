/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author bhk
 */
public class User {
    private int id;
    private String username;
    private String roles;
    private String img;

    public User(int id, String username, String roles, String img) {
        this.id = id;
        this.username = username;
        this.roles = roles;
        this.img = img;
    }

    public User() {
      //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username=" + username + ", roles=" + roles + ", img=" + img + '}';
    }

   
    
    
    
    
}
