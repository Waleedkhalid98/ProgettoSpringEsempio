package com.SpringBoot_SpringSecurity.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificaDTO {

    private long id; //lo teniamo per identificare il dto, infatti lo username non è sufficiente
    private String username;
    private String payload;
    private Boolean visualized;
    private Timestamp date;

    public NotificaDTO(String username, String payload, boolean visualized, Timestamp date) {
        this.username = username;
        this.payload = payload;
        this.visualized = visualized;
        this.date = date;
    }

}
