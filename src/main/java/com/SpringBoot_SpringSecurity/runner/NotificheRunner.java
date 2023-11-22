package com.SpringBoot_SpringSecurity.runner;

import com.SpringBoot_SpringSecurity.payload.NotificaDTO;
import com.SpringBoot_SpringSecurity.service.NotificaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;

@Component
public class NotificheRunner implements ApplicationRunner {
    @Autowired private NotificaService service;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //        createNotifica();
        //        service.deleteAllNotifiche();
    }

    public void createNotifica(){

        service.create(new NotificaDTO("carlino", "barbra dice che..",false, new Timestamp(new Date().getTime())));
        System.out.println("created notifica...");
    }
}
