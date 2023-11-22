package com.SpringBoot_SpringSecurity.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatUser1User2 {

    private String username1;
    private String username2;
    private List<ChatMessageDTO> messages;

}
