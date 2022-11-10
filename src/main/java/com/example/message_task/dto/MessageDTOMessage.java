package com.example.message_task.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class MessageDTOMessage {

    private String message;

    private MessageDTOMessage(String message) {
        this.message = message;
    }
}
