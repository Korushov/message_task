package com.example.message_task.util;

public class MessagesNotFoundException extends RuntimeException {
    public MessagesNotFoundException() {
        super("Messages not found!");
    }
}
