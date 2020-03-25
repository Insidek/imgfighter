package pl.poloch.imgfighter.api.priv;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.poloch.imgfighter.controllers.TimeController;

@RestController
public class UserPrivate {

    @GetMapping(value="/api/private/register")
    public TimeController getTime() {
        return new TimeController();
    }

}
