package com.example.message_task.controllers;

import com.example.message_task.dto.MessageDTO;
import com.example.message_task.dto.MessageDTOMessage;
import com.example.message_task.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @PostMapping("/message")
    public List<MessageDTOMessage> sendMessage(Principal principal, @RequestBody MessageDTO messageDTO){
        if (messageDTO.getName().equals(principal.getName())){
            return messageService.sendMessage(messageDTO);
        } else throw new UsernameNotFoundException("User not found!");
    }
}
