package com.SpringBoot_SpringSecurity.repository;

import com.SpringBoot_SpringSecurity.entity.ChatMessage;
import com.SpringBoot_SpringSecurity.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

        public List<ChatMessage> findByUser1AndUser2(User user1, User user2);


}
