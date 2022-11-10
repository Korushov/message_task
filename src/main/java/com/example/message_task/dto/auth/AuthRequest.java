package com.example.message_task.dto.auth;

import lombok.Data;

@Data
public class AuthRequest {
    private String name;
    private String password;
}
