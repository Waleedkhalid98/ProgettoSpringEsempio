package com.SpringBoot_SpringSecurity.service;

import com.SpringBoot_SpringSecurity.entity.ChatMessage;
import com.SpringBoot_SpringSecurity.entity.User;
import com.SpringBoot_SpringSecurity.payload.ChatMessageDTO;
import com.SpringBoot_SpringSecurity.payload.ChatUser1User2;
import com.SpringBoot_SpringSecurity.repository.ChatMessageRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatMessageService {

    @Autowired private ChatMessageRepository repo;

    @Autowired private UserService uService;

    //create
    public ChatMessageDTO create(ChatMessageDTO dto){
        repo.save(toEntity(dto));
        return dto;
    }

    //retrieve
    @Transactional //TODO: verificare getChatUser1ToUser2!!
    public List<ChatMessageDTO> getChatUser1ToUser2(User u1, User u2){ //returns the messages between user 1 and user 2
       List head= repo.findByUser1AndUser2(u1,u2).stream()
                .map(chatMessage -> toDTO(chatMessage))
                .toList(); //messaggi di u1 a u2
       var tail = repo.findByUser1AndUser2(u2,u1).stream()
                .map(chatMessage -> toDTO(chatMessage))
                .toList(); //messaggi di u2 a u1
        
        List res= new ArrayList<ChatMessageDTO>();

        head.forEach(message->res.add(message));
        tail.forEach(message->res.add(message));

        return res; //TODO: sistemare!!!!
    }


    /*returns the ChatUser1User2 object associated with the two usernames*/
    public ChatUser1User2 getChatUser1User2(String username1, String username2){

        var u1= uService.getByUsername(username1);
        var u2= uService.getByUsername(username2);

        return new ChatUser1User2(username1, username2, getChatUser1ToUser2(u1,u2));
    }

    public List<ChatMessageDTO> getChatUser1ToUser2(String uname1, String uname2){ //overloading
        var u1= uService.getByUsername(uname1);
        var u2= uService.getByUsername(uname2);
        return getChatUser1ToUser2(u1,u2);
    }

    public ChatMessageDTO toDTO(ChatMessage cm){
     return new ChatMessageDTO(cm.getId(), cm.getUser1().getUsername(), cm.getUser2().getUsername(), cm.getMessage(), cm.getTime());
    }
    public ChatMessage toEntity(ChatMessageDTO dto){
        var u1=uService.getByUsername(dto.getUsername1());
        var u2=uService.getByUsername(dto.getUsername2());
        return new ChatMessage(u1,u2,dto.getMessage(), dto.getTime());
    }

}
