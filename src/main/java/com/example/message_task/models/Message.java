package com.example.message_task.models;

import javax.persistence.*;

@Entity
@Table(name = "Message")
public class Message {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "message")
    private String message;

    @Column(name = "user_id")
    private int userId;
}
