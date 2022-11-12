package com.example.message_task.dto;


import lombok.*;

@Setter
@Getter
@NoArgsConstructor
public class MessageDTO {

    private String name;

    private String message;

    private MessageDTO(String message) {
        this.message = message;
    }

    public MessageDTO(String name, String message) {
        this.name = name;
        this.message = message;
    }
}
