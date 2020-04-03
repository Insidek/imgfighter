package pl.poloch.imgfighter.api.pub;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.poloch.imgfighter.controllers.general.JWTController;
import pl.poloch.imgfighter.controllers.general.JsonHTTPCode;
import pl.poloch.imgfighter.controllers.users.UserController;
import pl.poloch.imgfighter.controllers.users.UserAuth;
import pl.poloch.imgfighter.database_models.UserModel;
import pl.poloch.imgfighter.repositories.UserRepository;

import java.io.Serializable;


@RestController
public class UserPublic {

    Logger logger = LoggerFactory.getLogger(UserPublic.class);

    @Autowired
    UserRepository repository;

    @PostMapping(value="/api/public/register", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> register(@RequestBody UserController userController) {
        if (userController.nicknameAndEmailExist(repository)) {
            return JsonHTTPCode.BAD_REQUEST_USER_EMAIL_OR_NICKNAME_EXIST;
        }
        else if (userController.isNickname() && userController.isEmail() && userController.isPassword()) {
                userController.hashPassword();
                repository.save(new UserModel(userController.getNickname(), userController.getPassword(), userController.getEmail(), userController.getName(), userController.getSurname()));
                logger.info("User with the nickname: " + userController.getNickname() + " has been created!");
                return JsonHTTPCode.CREATED;
        } else {
            return JsonHTTPCode.BAD_REQUEST_USER_REQUIRE_FIELDS;
        }
    }

    @PostMapping(value="/api/public/auth", consumes = "application/json", produces = "application/json")
    public ResponseEntity<? extends Serializable> auth(@RequestBody UserAuth userAuth) {
        if (!(userAuth.isNickname() && userAuth.isPassword())) {
            return JsonHTTPCode.BAD_REQUEST_USER_REQUIRE_NICKNAME_AND_PASSWORD;
        }
        if (!(userAuth.existUser(repository))) {
            return JsonHTTPCode.BAD_REQUEST_USER_USER_NOT_EXIST;
        }
        if (userAuth.verifyPassword(repository)) {
            userAuth.setToken(JWTController.createJWT(userAuth.getNickname()));
            logger.info("User with the nickname: " + userAuth.getNickname() + " has been authorized!");
            return JsonHTTPCode.OK_BODY(userAuth);
        } else {
            return JsonHTTPCode.BAD_REQUEST_USER_WRONG_PASSWORD;
        }
    }


}
