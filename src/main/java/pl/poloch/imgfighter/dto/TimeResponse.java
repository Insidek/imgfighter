package pl.poloch.imgfighter.dto;

import java.time.LocalDateTime;

public class TimeResponse {

    private String serverTime = LocalDateTime.now().toString();

    public String getServerTime() {
        return serverTime;
    }
}
