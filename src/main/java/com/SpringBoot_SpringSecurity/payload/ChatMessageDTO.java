package com.SpringBoot_SpringSecurity.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessageDTO {

    private Long id;
    private String username1;
    private String username2;
    private String message;
    private Timestamp time;

    public ChatMessageDTO(String username1, String username2, String message, Timestamp time) {
        this.username1 = username1;
        this.username2 = username2;
        this.message = message;
        this.time = time;
    }
}
