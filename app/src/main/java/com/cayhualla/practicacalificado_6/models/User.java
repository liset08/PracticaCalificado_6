package com.cayhualla.practicacalificado_6.models;

import com.orm.dsl.Table;

/**
 * Created by Alumno on 20/04/2018.
 */
@Table
public class User {

    private Long id;
    private String email;
    private String fullname;
    private String username;
    private String password;

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", fullname='" + fullname + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }


    public User( String email, String fullname, String username, String password) {
        this.email = email;
        this.fullname = fullname;
        this.username = username;
        this.password = password;

    }




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }




}