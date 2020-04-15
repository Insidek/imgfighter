package pl.poloch.imgfighter.controllers.api.pub;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.poloch.imgfighter.dto.user.UserAuthRequest;
import pl.poloch.imgfighter.dto.user.UserRegisterRequest;
import pl.poloch.imgfighter.services.users.UserService;

import java.io.Serializable;

@RestController
public class UserPublicController {
    Logger logger = LoggerFactory.getLogger(UserPublicController.class);

    @Autowired
    UserService userService;


    @PostMapping(value="/api/public/register", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> register(@RequestBody UserRegisterRequest userRegisterRequest) {
        return userService.register(userRegisterRequest);
    }

    @PostMapping(value="/api/public/auth", consumes = "application/json", produces = "application/json")
    public ResponseEntity<? extends Serializable> auth(@RequestBody UserAuthRequest userAuthRequest) {
        return userService.auth(userAuthRequest);
    }
}
