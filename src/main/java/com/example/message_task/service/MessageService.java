package com.example.message_task.service;

import com.example.message_task.dto.MessageDTO;
import com.example.message_task.dto.MessageDTOMessage;
import com.example.message_task.models.Message;
import com.example.message_task.models.User;
import com.example.message_task.repository.MessageRepository;
import com.example.message_task.repository.UserRepository;
import com.example.message_task.util.MessagesNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MessageService {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public List<MessageDTOMessage> sendMessage(MessageDTO messageDTO) {
        if (!messageDTO.getMessage().equals("History 10")) {
            User user = userRepository.findByUsername(messageDTO.getName());
            Message message = modelMapper.map(messageDTO, Message.class);
            message.setMessage(message.getMessage());
            message.setUser(user);
            messageRepository.save(message);
            return null;
        } else {
            List<Message> messages = messageRepository.getTenLastMessages();
            if (messages.size()==0) throw new MessagesNotFoundException();
            List<MessageDTOMessage> messageDTOs = new ArrayList<>();
            for (Message m : messages) {
                MessageDTOMessage mapMessage = modelMapper.map(m, MessageDTOMessage.class);
                messageDTOs.add(mapMessage);
            }
            return messageDTOs;
        }
    }


}
