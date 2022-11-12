package com.example.message_task.service;

import com.example.message_task.dto.MessageDTO;
import com.example.message_task.dto.MessageDTOMessage;
import com.example.message_task.models.Message;
import com.example.message_task.models.User;
import com.example.message_task.repository.MessageRepository;
import com.example.message_task.repository.UserRepository;
import com.example.message_task.util.MessagesNotFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class MessageServiceTest {
    @Mock
    MessageRepository messageRepository;

    @Mock
    UserRepository userRepository;

    @Mock
    ModelMapper modelMapper;

    @InjectMocks
    MessageService messageService;


    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void sendMessageTest() {
        User user = new User("test");
        MessageDTO messageDTO = new MessageDTO("name", "message");
        Mockito.lenient().when(userRepository.findByUsername(messageDTO.getName())).thenReturn(user);
        Mockito.lenient().when(modelMapper.map(messageDTO, Message.class)).thenReturn(new Message(messageDTO.getMessage(), user));

        List<MessageDTOMessage> list = messageService.sendMessage(messageDTO);
        Assert.assertNull(list);
    }

    @Test
    public void emptyHistoryTest() {
        MessageDTO messageDTO = new MessageDTO("name", "History 10");
        Mockito.lenient().when(messageRepository.getTenLastMessages()).thenReturn(new ArrayList<>());
        try {
            List<MessageDTOMessage> list = messageService.sendMessage(messageDTO);
        } catch (MessagesNotFoundException e) {
            Assert.assertEquals("Messages not found!", e.getMessage());
        }
    }
}
