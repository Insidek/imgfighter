package pl.poloch.imgfighter.api.priv;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import pl.poloch.imgfighter.controllers.TimeController;
import pl.poloch.imgfighter.controllers.general.JWTController;


@RestController
public class UserPrivate {

    @GetMapping(value="/api/private/time")
    public TimeController getTime(@RequestHeader("Authorization") String token) {
        JWTController.verifyJWTToken(token);

        return new TimeController();
    }

}
