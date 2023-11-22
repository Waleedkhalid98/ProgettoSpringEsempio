package com.SpringBoot_SpringSecurity.payload;


import com.SpringBoot_SpringSecurity.entity.Sex;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserDetailsDTO {

    private String username;
    private String dogName;
    private String breed;
    private int dogAge;
    @Enumerated(EnumType.STRING)
    private Sex dogSex;
    private int dogWeight;
    private String description;
    private long idComune;


}
