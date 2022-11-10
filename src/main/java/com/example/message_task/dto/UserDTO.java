package com.example.message_task.dto;

public class UserDTO {
    private String name;
    private String password;

    public UserDTO(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public UserDTO() {
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
