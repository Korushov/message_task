package com.example.message_task.dto;

import lombok.*;

@AllArgsConstructor
@RequiredArgsConstructor
@Setter
@Getter
public class UserDTO {
    private String name;
    private String password;
}
