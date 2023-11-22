package com.SpringBoot_SpringSecurity.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LikeDTO {
    private String nameU1;
    private String nameU2;
    private Timestamp time;

    private boolean isReciprocal =false;

    public LikeDTO(String nameU1, String nameU2, Timestamp time) {
        this.nameU1 = nameU1;
        this.nameU2 = nameU2;
        this.time = time;
    }
}
