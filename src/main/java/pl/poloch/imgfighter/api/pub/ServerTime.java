package pl.poloch.imgfighter.api.pub;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.poloch.imgfighter.ImgFighterApplication;
import pl.poloch.imgfighter.controllers.TimeController;


@RestController
public class ServerTime {

    @GetMapping(value="/api/public/time")
    public TimeController getServerTime() {
        System.out.println(ImgFighterApplication.imagesDirectory);
        return new TimeController();
    }
}
