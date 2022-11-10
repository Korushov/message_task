package com.example.message_task.controllers;

import lombok.Data;

@Data
public class AuthRequest {
    private String name;
    private String password;
}
