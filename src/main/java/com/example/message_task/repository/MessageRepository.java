package com.example.message_task.repository;

import com.example.message_task.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {

    @Query(nativeQuery = true, value = "Select * from (select * from message order by id desc limit 10) as new order by id")
    List<Message> getTenLastMessages();

}
