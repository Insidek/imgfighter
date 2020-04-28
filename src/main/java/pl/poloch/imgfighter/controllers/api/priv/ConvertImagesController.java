package pl.poloch.imgfighter.controllers.api.priv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.poloch.imgfighter.dto.images.ConvertImagesRequest;
import pl.poloch.imgfighter.services.general.JWTService;
import pl.poloch.imgfighter.services.images.ConvertImagesService;

import java.io.IOException;


@RestController
public class ConvertImagesController {

    @Autowired
    ConvertImagesService convertImagesService;

    @GetMapping(value="/api/private/img/convert", produces = "image/png")
    public ResponseEntity<byte[]> convert(@RequestHeader("Authorization") String token, ConvertImagesRequest convertImagesRequest) throws IllegalAccessException, NoSuchFieldException, IOException {
        JWTService.verifyJWTToken(token);

        return convertImagesService.convert(convertImagesRequest);
    }
}
