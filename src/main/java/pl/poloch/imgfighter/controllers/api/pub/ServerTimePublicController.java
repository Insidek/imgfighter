package pl.poloch.imgfighter.controllers.api.pub;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.poloch.imgfighter.dto.TimeResponse;


@RestController
public class ServerTimePublicController {

    @GetMapping(value="/api/public/time")
    public TimeResponse getServerTime() {
        return new TimeResponse();
    }
}
