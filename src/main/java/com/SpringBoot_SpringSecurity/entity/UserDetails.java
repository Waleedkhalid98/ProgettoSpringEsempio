package com.SpringBoot_SpringSecurity.entity;

import com.SpringBoot_SpringSecurity.payload.UserDetailsDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="user_details")
public class UserDetails {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name="user_id")
    private User user;

    private String name;

    private String dogName;
    private String breed;
    private int dogAge;
    private String description;

    @Enumerated(EnumType.STRING)
    private Sex dogSex;
    private int dogWeight;
    @ManyToOne
    @JoinColumn(name="user_comune")
    private BeServiceComuni comune;

    public UserDetails(User user, String name, String dogName, String breed, int dogAge, Sex dogSex, int dogWeight,String description, BeServiceComuni comune) {
        this.user = user;
        this.name = name;
        this.dogName = dogName;
        this.breed = breed;
        this.dogAge = dogAge;
        this.dogSex = dogSex;
        this.dogWeight = dogWeight;
        this.description=description;
        this.comune = comune;
    }

    public UserDetails(User user, String name,  String dogName, String breed, int dogAge, Sex dogSex, int dogWeight,String description) {
        this.user = user;
        this.name = name;
        this.dogName = dogName;
        this.breed = breed;
        this.dogAge = dogAge;
        this.dogSex = dogSex;
        this.dogWeight = dogWeight;
        this.description=description;
    }

    public UserDetails(String name, String dogName, Sex dogSex,String description) {
        this.name = name;
        this.dogName = dogName;
        this.dogSex=dogSex;
        this.description=description;
    }

    public UserDetailsDTO getInfoUser(){
        long idComune=99;
        if(comune!=null){idComune=comune.getId();}
    return new UserDetailsDTO(user.getUsername(), dogName, breed, dogAge, dogSex, dogWeight, description, idComune );

    }
}
