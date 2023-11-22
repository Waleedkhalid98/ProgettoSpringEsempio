package com.SpringBoot_SpringSecurity.controller;

import com.SpringBoot_SpringSecurity.payload.ChatMessageDTO;
import com.SpringBoot_SpringSecurity.payload.ChatUser1User2;
import com.SpringBoot_SpringSecurity.service.ChatMessageService;
import com.SpringBoot_SpringSecurity.service.LikeService;
import com.SpringBoot_SpringSecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chatMessage")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ChatMessagesController {

    @Autowired private ChatMessageService service;
    @Autowired private LikeService likeService;
    @Autowired private UserService uService;
    @PostMapping
    public ResponseEntity<ChatMessageDTO> postChatMessage(@RequestBody ChatMessageDTO dto){
        service.create(dto);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/{uname1}/{uname2}")
    public ResponseEntity<List<ChatMessageDTO>> getChat12(@PathVariable String uname1, @PathVariable String uname2){
        return new ResponseEntity<>(service.getChatUser1ToUser2(uname1, uname2), HttpStatus.OK);
    }


    /*
    * ritorna lista delle chat dello user con i suoi crushes:
    * 1. prende i crushes con il likeService
    * 2. per ogni crush prende la chat associata
    * 3. ritorna la lista delle chat
    * */
    @GetMapping("/allMessagesUser/{username}")
    public ResponseEntity<List<ChatUser1User2>> getChatsOfUser(@PathVariable String username){


        var listOfChats= likeService.crushesOfUser(uService.getByUsername(username)).stream() //1.
                .map(crush-> service.getChatUser1User2(username, crush.getUsername())) //2.
                .toList();
        return new ResponseEntity<>(listOfChats, HttpStatus.OK);
        }

    @GetMapping("/test")
    public ResponseEntity<String> test(){

        return new ResponseEntity<>("non è accettabile", HttpStatus.NOT_ACCEPTABLE);
    }

}
