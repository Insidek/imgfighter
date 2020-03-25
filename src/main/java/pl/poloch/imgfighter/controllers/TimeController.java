package pl.poloch.imgfighter.controllers;


import java.time.LocalDateTime;

public class TimeController {

    private final String serverTime;

    public TimeController() {
        this.serverTime = LocalDateTime.now().toString();
    }

    public String getServerTime() {
        return serverTime;
    }
}

