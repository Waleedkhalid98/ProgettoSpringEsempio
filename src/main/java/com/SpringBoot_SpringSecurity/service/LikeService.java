package com.SpringBoot_SpringSecurity.service;

import com.SpringBoot_SpringSecurity.entity.Like;
import com.SpringBoot_SpringSecurity.entity.User;
import com.SpringBoot_SpringSecurity.payload.LikeDTO;
import com.SpringBoot_SpringSecurity.repository.LikeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeService {

    @Autowired private LikeRepository repo;

    @Autowired private UserService userService;

    //TODO: likes---> create, retrieve, update, delete

    //create
    public Like create(Like like){
        repo.save(like);
        return like;
    }

    //retrieve
    public List<Like> getUserLikesSent(User u){
        return repo.findByUser1(u);
    }

    public List<Like> getUserLikesReceived(User u){
        return repo.findByUser2(u);
    }

    public boolean isReciprocal(User u1, User u2){ //TODO: verificare siPiacciono()
        //ritorna booleano che dice se tra i due user c'è like ricambiato

        var likes12=repo.findByUser1AndUser2(u1,u2).size();
        var likes21=repo.findByUser1AndUser2(u2,u1).size();

        return (likes21+likes12) == 2 ? true : false;

    }
    public boolean isReciprocal(LikeDTO likeDTO){
        var u1=userService.getByUsername(likeDTO.getNameU1());
        var u2=userService.getByUsername(likeDTO.getNameU2());
        return isReciprocal(u1,u2);
    }

    public List<User> crushesOfUser(User u){ //TODO: testare
        return getUserLikesSent(u).stream()
                .filter(like -> isReciprocal(toDTO(like))) //filtro sui likes reciproci
                .map(like -> like.getUser2())
                .toList();
    }


    //helper methods
    @Transactional
    public Like toEntity(LikeDTO likeDTO){

        var u1=userService.getByUsername(likeDTO.getNameU1());
        var u2=userService.getByUsername(likeDTO.getNameU2());
        Like like = new Like(u1, u2, likeDTO.getTime());
        return create(like);
    }
    public LikeDTO toDTO(Like like){
        var dto= new LikeDTO(like.getUser1().getUsername(), like.getUser2().getUsername(), like.getTime());
        dto.setReciprocal(isReciprocal(dto));
        return dto;
    }
}
