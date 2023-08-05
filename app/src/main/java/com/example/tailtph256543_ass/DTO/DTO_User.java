package com.example.tailtph256543_ass.DTO;

import java.io.Serializable;

public class DTO_User implements Serializable {
    private int id;
    private String username;
    private String email;
    private String password;
    private String fullname;

    public DTO_User() {
    }

    public DTO_User(int id, String username, String email, String password, String fullname) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
}
