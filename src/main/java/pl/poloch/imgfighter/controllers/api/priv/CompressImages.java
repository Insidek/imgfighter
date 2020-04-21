package pl.poloch.imgfighter.controllers.api.priv;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.poloch.imgfighter.services.general.JWTService;
import pl.poloch.imgfighter.services.images.CompressImagesController;
import pl.poloch.imgfighter.services.images.ImagesService;

import java.io.IOException;


@RestController
public class CompressImages {

    @GetMapping(value="/api/private/img/compress", produces = "image/png")
    public ResponseEntity<byte[]> compress(@RequestHeader("Authorization") String token, @RequestParam(required = false) Float quality, String format, @RequestParam(required = true) String url) throws IOException {
        JWTService.verifyJWTToken(token);
        // IF only url is not NULL
        if (quality == null && format == null) {
            return ImagesService.uploadImage(new CompressImagesController(ImagesService.downloadImage(url)).compress());
        }
        // IF url and quality is not NULL
        if (quality != null && format == null) {
            return ImagesService.uploadImage(new CompressImagesController(ImagesService.downloadImage(url), quality).compress());
        }
        // IF url and format is not NULL
        if (quality == null && format != null) {
            return ImagesService.uploadImage(new CompressImagesController(ImagesService.downloadImage(url), format).compress());
        }
        // IF url and quality and format is not NULL
        if  (quality != null && format != null) {
            return ImagesService.uploadImage(new CompressImagesController(ImagesService.downloadImage(url), quality, format).compress());
        }

        throw new RuntimeException("This shouldn't happen!");
    }
}
