package com.SpringBoot_SpringSecurity.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="notifiche")
public class Notifica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_utente")
    private User user;

    private String payload;

    private Boolean visualized;
    private Timestamp date;

    public Notifica(User user, String payload, boolean visualized, Timestamp date) {
        this.user = user;
        this.payload = payload;
        this.visualized = visualized;
        this.date = date;
    }




}
