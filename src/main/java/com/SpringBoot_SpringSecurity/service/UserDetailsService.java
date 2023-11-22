package com.SpringBoot_SpringSecurity.service;

import com.SpringBoot_SpringSecurity.entity.User;
import com.SpringBoot_SpringSecurity.entity.UserDetails;
import com.SpringBoot_SpringSecurity.payload.UserDetailsDTO;
import com.SpringBoot_SpringSecurity.repository.UserDetailsRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService {


    @Autowired private UserDetailsRepository repo;

    @Autowired private UserService uService;
    //crud ops

    //create
    public UserDetails create(UserDetails d){
        repo.save(d);
        return d;
    }


    /*
    * partendo da un UserDetailsDTO fa quanto segue:
    * 1. usa username per risalire ad user
    * 2. verifica se user ha già UserDetails in DB: se sì, li aggiorna, se no, li crea
    * 3. ritorna i UserDetails aggiornati
    * */
    public UserDetailsDTO updateFromUserDetailsDTO(UserDetailsDTO info){ //TODO: modificare aggiungendo il comune

           var user= uService.getByUsername(info.getUsername());

           repo.findUserDetailsByUsername(user.getUsername()).ifPresentOrElse(
                    value->{
                        value.setBreed(info.getBreed());
                        value.setDogAge(info.getDogAge());
                        value.setDogName(info.getDogName());
                        value.setDogWeight(info.getDogWeight());
                        value.setDogSex(info.getDogSex());//aggiungere trattamento comune
                        value.setDescription(info.getDescription());
                        repo.save(value);
                        },
                    ()->{ repo.save(new UserDetails(user, user.getName(), info.getDogName(), info.getBreed(), info.getDogAge(), info.getDogSex(), info.getDogWeight(), info.getDescription(), null));}
            );
           return getByUser(user).getInfoUser();
    }

    public UserDetails createWithUserId(UserDetails userDetails, Long uId){ //TODO: TEST create with userID
        var user=uService.getById(uId);
        userDetails.setUser(user);
        repo.save(userDetails);
        return userDetails;
    }


    //retrieve
    public Page<UserDetails> getAll(Pageable pageable){
        return repo.findAll(pageable);
    }

    public Page<UserDetailsDTO> getAllInfoUsers(Pageable pageable){
        var list= repo.findAll().stream().map(userDetails -> userDetails.getInfoUser()).toList();
        return  PageableExecutionUtils.getPage(list, pageable, () -> list.size());
        }
    public UserDetails getByUserID(Long idUser){ //TODO: testare, eventualmente abbiamo il metodo seguente
        return repo.findByUserId(idUser).orElseThrow(()->new EntityNotFoundException("user con questo id non esiste!"));
    }

    public UserDetails getByUser(User u){
        return repo.findByUser(u).orElseThrow(()->new EntityNotFoundException("user con questo id non esiste!"));
    }
    public UserDetails getByUsername(String username){
    return repo.findUserDetailsByUsername(username).orElseThrow(()->new EntityNotFoundException("details associated with this username don't exist"));
    }

    //update
    public UserDetails update(UserDetails userDetails){
        repo.save(userDetails);
        return userDetails;
    }

    public UserDetails updateWithUserId(UserDetails userDetails, Long uId){ //TODO: TEST update with userID
        var user=uService.getById(uId);
        userDetails.setUser(user);
        repo.save(userDetails);
        return userDetails;
    }

}
