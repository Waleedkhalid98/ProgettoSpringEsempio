package com.SpringBoot_SpringSecurity.controller;

import com.SpringBoot_SpringSecurity.payload.LikeDTO;
import com.SpringBoot_SpringSecurity.service.LikeService;
import com.SpringBoot_SpringSecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/likes")
@CrossOrigin(origins = "*", maxAge = 3600)
public class LikesController {

   @Autowired private LikeService service;

   @Autowired private UserService uService;
   //create
    @PostMapping
    public ResponseEntity<LikeDTO> createLike(@RequestBody LikeDTO likeDTO){
        service.toEntity(likeDTO);
        likeDTO.setReciprocal(service.isReciprocal(likeDTO));
        return new ResponseEntity<>(likeDTO, HttpStatus.OK);
    }

    //read
    @GetMapping("/byUsernameLiker/{username}") //ritorna gli usernames utenti a cui l'utente ha messo like
    public ResponseEntity<List<String>> getLikesByUsernameLikerUser(@PathVariable String username){

        var listUsernameLiked=service.getUserLikesSent(uService.getByUsername(username))
                .stream()
                .map(like-> like.getUser2().getUsername())
                .toList();

        return new ResponseEntity<>(listUsernameLiked, HttpStatus.OK);
    }


    @GetMapping("/byUsernameLiked/{username}") //ritorna lista username utenti che hanno messo like all'utente
    public ResponseEntity<List<String>> getLikesByUsernameLikedUser(@PathVariable String username){

        var listUsernamesLikers=service.getUserLikesReceived(uService.getByUsername(username))
                .stream()
                .map(like->like.getUser1().getUsername())
                .toList();
        return new ResponseEntity<>(listUsernamesLikers, HttpStatus.OK);
    }

}
