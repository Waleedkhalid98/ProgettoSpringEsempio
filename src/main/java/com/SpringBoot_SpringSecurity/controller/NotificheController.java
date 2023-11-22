package com.SpringBoot_SpringSecurity.controller;

import com.SpringBoot_SpringSecurity.payload.NotificaDTO;
import com.SpringBoot_SpringSecurity.service.NotificaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api/notifications")
@CrossOrigin(origins = "*", maxAge = 3600)
public class NotificheController {

    @Autowired private NotificaService service;


    //post
    @PostMapping
    public ResponseEntity<NotificaDTO> postNotifica(@RequestBody NotificaDTO dto){
        return new ResponseEntity<>(service.create(dto), HttpStatus.OK);
    }

    //get
    @GetMapping("/{username}")
    public ResponseEntity<List<NotificaDTO>> getAllNotificheOfUser(@PathVariable String username){
        return new ResponseEntity<>(service.getNotificheByUsername(username), HttpStatus.OK);
    }


    //update
    @PutMapping
    public ResponseEntity<List<NotificaDTO>> makeNotificheVisualized(@RequestBody List<Long> listaIdNotificheDaAggiornare){

        var res= listaIdNotificheDaAggiornare.stream()
                .map(id-> service.makeVisualized(id))
                .toList();

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    //delete
    @DeleteMapping("/singleNotification")
    public ResponseEntity<NotificaDTO> deleteNotifica(@RequestBody NotificaDTO dto){
        return new ResponseEntity<>(service.deleteNotificaByDto(dto), HttpStatus.OK);
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<List<NotificaDTO>> deleteNotificheOfUser(@PathVariable String username){
        var listNotificheDto= service.getNotificheByUsername(username);
            listNotificheDto.forEach(notificaDTO -> service.deleteNotificaByDto(notificaDTO));
            return new ResponseEntity<>(listNotificheDto, HttpStatus.OK);

    }



}