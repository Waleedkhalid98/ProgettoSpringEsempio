package com.SpringBoot_SpringSecurity.repository;

import com.SpringBoot_SpringSecurity.entity.ProfilePic;
import com.SpringBoot_SpringSecurity.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfilePicRepository extends JpaRepository<ProfilePic, Long> {
    public List<ProfilePic> findProfilePicsByUser(User u);
}
