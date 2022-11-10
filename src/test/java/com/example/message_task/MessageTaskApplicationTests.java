package com.example.message_task;

import com.example.message_task.dto.MessageDTO;
import com.example.message_task.models.Message;
import com.example.message_task.models.User;
import com.example.message_task.repository.MessageRepository;
import com.example.message_task.repository.UserRepository;
import com.example.message_task.service.MessageService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
class MessageTaskApplicationTests {

	@Autowired
	private MessageService messageService;

	@MockBean
	private UserRepository userRepository;

	@MockBean
	private MessageRepository messageRepository;

	@Autowired
	private ModelMapper mapper;

	private static final User USER = new User(-10, "test", "test", null);
	private static final Message MESSAGE=new Message(-10, "Привет", USER);
	private static final Message MESSAGE2=new Message(-10, "History 10", USER);
	public static final List<Message> messages = new ArrayList<>();

	@Test
	public void sendMessageTest(){
		when(userRepository.findByUsername(USER.getUsername())).thenReturn(USER);
		MessageDTO messageDTO=mapper.map(MESSAGE, MessageDTO.class);
		messageService.sendMessage(messageDTO);
		verify(messageRepository).save(any());
	}

	@Test
	public void sendMessageShowHistoryTest(){
		when(userRepository.findByUsername(USER.getUsername())).thenReturn(USER);
		List<Message> messages=new ArrayList<>();
		messages.add(MESSAGE);
		when(messageRepository.getTenLastMessages()).thenReturn(messages);
		MessageDTO messageDTO=mapper.map(MESSAGE2, MessageDTO.class);
		messageService.sendMessage(messageDTO);
		verify(messageRepository).getTenLastMessages();
	}

}



