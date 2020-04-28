package pl.poloch.imgfighter.controllers.api.priv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import pl.poloch.imgfighter.dto.images.CompressImageRequest;
import pl.poloch.imgfighter.services.general.JWTService;
import pl.poloch.imgfighter.services.images.CompressImagesService;


import java.io.IOException;


@RestController
public class CompressImagesController {

    @Autowired
    CompressImagesService compressImagesService;

    @GetMapping(value="/api/private/img/compress", produces = "image/png")
    public ResponseEntity<byte[]> compress(@RequestHeader("Authorization") String token, CompressImageRequest compressImageRequest) throws IOException {
        JWTService.verifyJWTToken(token);

        return compressImagesService.compress(compressImageRequest);
    }
}
