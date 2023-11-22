package com.SpringBoot_SpringSecurity.service;


import com.SpringBoot_SpringSecurity.entity.Notifica;
import com.SpringBoot_SpringSecurity.payload.NotificaDTO;
import com.SpringBoot_SpringSecurity.repository.NotificaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificaService {


    @Autowired private NotificaRepository repo;
    @Autowired private UserService uService;

    //create
    @Transactional
    public NotificaDTO create(NotificaDTO dto){
        var notifica=repo.save(toEntity(dto));
        System.out.println("saved in db notifica: "+ dto.toString());
        return dto;
    }

    //retrieve
    public List<NotificaDTO> getNotificheByUsername(String username){
        var user= uService.getByUsername(username);
        return repo.getNotificasByUser(user).stream()
                .map(notifica -> toDTO(notifica))
                .toList();

    }

    //update

    /*
    * prende id di una notifica per renderla visualized,
    * quindi ritorna il dto corrispondente aggiornato
    * */
    public NotificaDTO makeVisualized(long id){
        var notifica= repo.findById(id).orElseThrow(()->new EntityNotFoundException("non esiste una notifica con quetso id: "+ id));
        notifica.setVisualized(true);
        repo.save(notifica);
        return toDTO(notifica);
    }


    //delete
    public NotificaDTO deleteNotificaByDto(NotificaDTO dto){
        repo.deleteById(dto.getId());
        System.out.println("deleted notifica "+dto.toString());
        return dto;
    }

    public void deleteAllNotifiche(){
        repo.deleteAll();
        System.out.println("deleted all notifiche in db");
    }

    //utility methods
    public NotificaDTO toDTO(Notifica notifica){
        return new NotificaDTO(notifica.getId(), notifica.getUser().getUsername(), notifica.getPayload(), notifica.getVisualized(), notifica.getDate());
    }

    public Notifica toEntity(NotificaDTO dto){
        var user= uService.getByUsername(dto.getUsername());
        return new Notifica(user, dto.getPayload(), dto.getVisualized(), dto.getDate());
    }

}
