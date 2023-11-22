package com.SpringBoot_SpringSecurity.controller;

import com.SpringBoot_SpringSecurity.payload.ProfilePicDTO;
import com.SpringBoot_SpringSecurity.service.ProfilePicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profilePics")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ProfilePicController {


    @Autowired private ProfilePicService service;


    //POST
    @PostMapping
    public ResponseEntity<ProfilePicDTO> postPic(@RequestBody ProfilePicDTO dto){
        return new ResponseEntity<>(service.create(dto), HttpStatus.OK);
    }

    //GET
    @GetMapping("/{username}")
    public ResponseEntity<List<ProfilePicDTO>> getPicsByUsername(@PathVariable String username){
        return new ResponseEntity<>(service.getByUsername(username), HttpStatus.OK);
    }


}
