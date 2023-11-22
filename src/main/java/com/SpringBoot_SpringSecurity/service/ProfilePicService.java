package com.SpringBoot_SpringSecurity.service;

import com.SpringBoot_SpringSecurity.entity.ProfilePic;
import com.SpringBoot_SpringSecurity.payload.ProfilePicDTO;
import com.SpringBoot_SpringSecurity.repository.ProfilePicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfilePicService {

    @Autowired private ProfilePicRepository repo;
    @Autowired private UserService uService;

    //CREATE

    public ProfilePicDTO create(ProfilePicDTO dto){
       return toDTO(repo.save(toEntity(dto)));
    }

    //READ

    public List<ProfilePicDTO> getByUsername(String username){

        var u= uService.getByUsername(username);
        return repo.findProfilePicsByUser(u)
                .stream()
                .map(profilePic->toDTO(profilePic))
                .toList();
    }


    //utility methods
    public ProfilePic toEntity(ProfilePicDTO dto){
        var u= uService.getByUsername(dto.getUsername());
        return new ProfilePic(dto.getPublic_id(), u);
    }
    public ProfilePicDTO toDTO(ProfilePic entity){
        return new ProfilePicDTO(entity.getId(), entity.getPublicId(), entity.getUser().getUsername());
    }


}
