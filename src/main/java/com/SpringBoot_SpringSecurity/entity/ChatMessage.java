package com.SpringBoot_SpringSecurity.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "chat")
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    @ManyToOne
    @JoinColumn(name="id_user1")
    private User user1;

    @NonNull
    @ManyToOne
    @JoinColumn(name="id_user2")
    private User user2;

    @NonNull
    @Column(name="message")
    private String message;

    @NonNull
    @Column(name="time")
    private Timestamp time;


}