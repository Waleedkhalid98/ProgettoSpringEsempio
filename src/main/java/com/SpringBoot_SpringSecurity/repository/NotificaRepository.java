package com.SpringBoot_SpringSecurity.repository;

import com.SpringBoot_SpringSecurity.entity.Notifica;
import com.SpringBoot_SpringSecurity.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificaRepository extends JpaRepository<Notifica, Long> {



    public List<Notifica> getNotificasByUser(User u);
}
