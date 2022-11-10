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
}
