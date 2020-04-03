package pl.poloch.imgfighter.api.priv;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.poloch.imgfighter.controllers.general.JWTController;
import pl.poloch.imgfighter.controllers.images.CompressImagesController;
import pl.poloch.imgfighter.controllers.images.ImagesController;

import java.io.IOException;


@RestController
public class CompressImages {

    @GetMapping(value="/api/private/img/compress", produces = "image/png")
    public ResponseEntity<byte[]> compress(@RequestHeader("Authorization") String token, @RequestParam(required = false) Float quality, String format, @RequestParam(required = true) String url) throws IOException {
        JWTController.verifyJWTToken(token);
        // IF only url is not NULL
        if (quality == null && format == null) {
            return ImagesController.uploadImage(new CompressImagesController(ImagesController.downloadImage(url)).compress());
        }
        // IF url and quality is not NULL
        if (quality != null && format == null) {
            return ImagesController.uploadImage(new CompressImagesController(ImagesController.downloadImage(url), quality).compress());
        }
        // IF url and format is not NULL
        if (quality == null && format != null) {
            return ImagesController.uploadImage(new CompressImagesController(ImagesController.downloadImage(url), format).compress());
        }
        // IF url and quality and format is not NULL
        if  (quality != null && format != null) {
            return ImagesController.uploadImage(new CompressImagesController(ImagesController.downloadImage(url), quality, format).compress());
        }

        throw new RuntimeException("This shouldn't happen!");
    }
}
