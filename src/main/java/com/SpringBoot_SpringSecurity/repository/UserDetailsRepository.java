package com.SpringBoot_SpringSecurity.repository;

import com.SpringBoot_SpringSecurity.entity.User;
import com.SpringBoot_SpringSecurity.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {

        public Optional<UserDetails> findByUserId(Long id);

        public Optional<UserDetails> findByUser(User user);
        @Query("SELECT d FROM UserDetails d JOIN d.user u WHERE u.username = ?1")
        Optional<UserDetails> findUserDetailsByUsername (String u);

        boolean existsByUser(User u);
}
