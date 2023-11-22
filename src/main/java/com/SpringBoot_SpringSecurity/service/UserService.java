package com.SpringBoot_SpringSecurity.service;

import com.SpringBoot_SpringSecurity.entity.User;
import com.SpringBoot_SpringSecurity.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired private UserRepository repo;
    //retrieve
    public User getById(Long id){
        return repo.findById(id).orElseThrow(()->new EntityNotFoundException("user con questo id non esiste!!"));
    }
    public User getByUsername(String username){
        return repo.findByUsername(username).orElseThrow(()->new EntityNotFoundException("user con questo username"+ " '"+username +"' " +"non esiste!!"));
    }

    //delete
    public User delete(User u){
        repo.delete(u);
        return u;
    }


}
