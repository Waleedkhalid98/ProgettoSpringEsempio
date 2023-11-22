package com.SpringBoot_SpringSecurity.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class ProfilePicDTO {

    private Long id;
    private String public_id;
    private String username;

    public ProfilePicDTO(String public_id, String username) {
        this.public_id = public_id;
        this.username=username;
    }
}
