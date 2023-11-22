package com.SpringBoot_SpringSecurity.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="profile_pics")
public class ProfilePic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="public_id")
    private String publicId;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    public ProfilePic(String publicId, User user) {
        this.publicId = publicId;
        this.user = user;
    }
}
