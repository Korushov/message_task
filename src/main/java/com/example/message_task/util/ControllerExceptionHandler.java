package com.example.message_task.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {
    //Пользователь или сообщение не найдено в базе данных
    @ExceptionHandler(value={UsernameNotFoundException.class, MessagesNotFoundException.class})
    public ResponseEntity<String> notFoundException(Exception exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    //Для существующего пользователя введен неверный пароль
    @ExceptionHandler(value={BadCredentialsException.class})
    public ResponseEntity<String> unauthorizedException(Exception exception){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(exception.getMessage());
    }


}