package pl.poloch.imgfighter.controllers.api.priv;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import pl.poloch.imgfighter.dto.TimeResponse;
import pl.poloch.imgfighter.services.general.JWTController;


@RestController
public class ServerTimePrivateController {

    @GetMapping(value="/api/private/time")
    public TimeResponse getTime(@RequestHeader("Authorization") String token) {
        JWTController.verifyJWTToken(token);

        return new TimeResponse();
    }

}
