package com.SpringBoot_SpringSecurity.runner;

import com.SpringBoot_SpringSecurity.entity.Sex;
import com.SpringBoot_SpringSecurity.entity.UserDetails;
import com.SpringBoot_SpringSecurity.payload.UserDetailsDTO;
import com.SpringBoot_SpringSecurity.repository.UserRepository;
import com.SpringBoot_SpringSecurity.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsRunner implements ApplicationRunner {

    @Autowired private UserDetailsService service;
    @Autowired private UserRepository userRepo;
    @Override
    public void run(ApplicationArguments args) throws Exception {
    }




    public void testUserDetails(){
         UserDetailsDTO details= new UserDetailsDTO("Barbra", "Stellina", "hane stiloso da borsetta", 2,Sex.F, 99, "Il mi hane è un hane di luxxo che o hompratho per il mi hompleanno. Io son ddi PPisa, ma vivo a Firenze. ", 99);
        service.updateFromUserDetailsDTO(details);
        System.out.println("detail added");
    }

    public void popolaDB(){
        UserDetails adminDetails= new UserDetails("Clara", "CocoLavastoviglie", Sex.M, "cane bianco puro razza superior");
        service.createWithUserId(adminDetails, 1l);
    }

    public void deleteUser(){
        userRepo.deleteById(11l);
        System.out.println("deleted");
    }


}
