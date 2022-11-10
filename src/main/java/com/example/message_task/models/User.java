package com.example.message_task.models;

import javax.persistence.*;

@Entity
@Table(name= "User")
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(name = "Name")
    private String name;

    @Column(name = "password")
    private String password;

    public User(int userId, String name, String password) {
        this.userId = userId;
        this.name = name;
        this.password = password;
    }

    public User() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
