package com.SpringBoot_SpringSecurity.repository;

import com.SpringBoot_SpringSecurity.entity.Like;
import com.SpringBoot_SpringSecurity.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {

    public List<Like> findByUser1(User u);
    public List<Like> findByUser2(User u);

    public List<Like> findByUser1AndUser2(User u1, User u2);


}
