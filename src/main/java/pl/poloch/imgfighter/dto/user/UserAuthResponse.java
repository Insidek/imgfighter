package pl.poloch.imgfighter.dto.user;

import java.io.Serializable;

public class UserAuthResponse implements Serializable {
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
