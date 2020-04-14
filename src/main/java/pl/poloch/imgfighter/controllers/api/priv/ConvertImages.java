package pl.poloch.imgfighter.controllers.api.priv;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.poloch.imgfighter.services.general.JWTController;
import pl.poloch.imgfighter.services.images.ConvertImagesController;
import pl.poloch.imgfighter.services.images.ImagesController;

import java.io.IOException;


@RestController
public class ConvertImages {
    @GetMapping(value="/api/private/img/convert", produces = "image/png")
    public ResponseEntity<byte[]> convert(@RequestHeader("Authorization") String token, @RequestParam(required = false) String format, String bgcolor, @RequestParam(required = true) String url) throws IOException, NoSuchFieldException, IllegalAccessException {
        JWTController.verifyJWTToken(token);
        // IF only url is not NULL
        if (bgcolor == null && format == null) {
            return ImagesController.uploadImage(new ConvertImagesController(ImagesController.downloadImage(url)).convert());
        }
        // IF url and bgcolor is not NULL
        if (bgcolor != null && format == null) {
            return ImagesController.uploadImage(new ConvertImagesController(ImagesController.downloadImage(url), bgcolor).convert());
        }
        // IF url and format is not NULL
        if (bgcolor == null && format != null) {
            return ImagesController.uploadImage(new ConvertImagesController(ImagesController.downloadImage(url), format).convert());
        }
        // IF url and bgcolor and format is not NULL
        if  (bgcolor != null && format != null) {
            return ImagesController.uploadImage(new ConvertImagesController(ImagesController.downloadImage(url), format, bgcolor).convert());
        }


        throw new RuntimeException("This shouldn't happen!");
    }
}
