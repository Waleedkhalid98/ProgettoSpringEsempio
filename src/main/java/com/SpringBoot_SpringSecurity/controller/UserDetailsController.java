package com.SpringBoot_SpringSecurity.controller;

import com.SpringBoot_SpringSecurity.entity.UserDetails;
import com.SpringBoot_SpringSecurity.payload.UserDetailsDTO;
import com.SpringBoot_SpringSecurity.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/details")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserDetailsController {

    @Autowired private UserDetailsService service;

    //CRUD

    //CREATE
    @PostMapping
    public ResponseEntity<UserDetailsDTO> postUserDetails(@RequestBody UserDetailsDTO userDetailsDTO){

        return new ResponseEntity<>(service.updateFromUserDetailsDTO(userDetailsDTO), HttpStatus.OK);
    }

    //RETRIEVE
    @GetMapping("/{id}")
    public ResponseEntity<UserDetailsDTO> getUserDetails(@PathVariable Long id){
        return new ResponseEntity<>(service.getByUserID(id).getInfoUser(), HttpStatus.OK);
    }

    @GetMapping("/all/{page}")
    public ResponseEntity<Page<UserDetailsDTO>> getAll(@PathVariable int page, //TODO: far diventare InfoUser
                                                       @RequestParam(defaultValue = "10") int size,
                                                       @RequestParam(defaultValue = "id") String sortBy){
        Pageable sortedByName = PageRequest.of(page, size, Sort.by(sortBy));
        return new ResponseEntity<>(service.getAllInfoUsers(sortedByName), HttpStatus.OK);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<UserDetailsDTO> getByUsername(@PathVariable String username){
    return new ResponseEntity<>(service.getByUsername(username).getInfoUser(), HttpStatus.OK);
    }

}
