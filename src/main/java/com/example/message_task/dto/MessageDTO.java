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
}
