package pl.poloch.imgfighter.api.pub;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.poloch.imgfighter.controllers.JsonHTTPCode;
import pl.poloch.imgfighter.controllers.users.UserController;
import pl.poloch.imgfighter.database_models.UserModel;
import pl.poloch.imgfighter.repositories.UserRepository;



@RestController
public class UserPublic {

    private static final Logger log = LoggerFactory.getLogger(UserPublic.class);

    @Autowired
    UserRepository repository;

    @PostMapping(value="/api/public/register", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> register(@RequestBody UserController userController) {
        if (userController.nicknameAndEmailExist(repository)) { return JsonHTTPCode.BAD_REQUEST_USER_EMAIL_OR_NICKNAME_EXIST; }
        else if (userController.requiredFieldsNotNull()) {
                userController.hashPassword();
                repository.save(new UserModel(userController.getNickname(), userController.getPassword(), userController.getEmail(), userController.getName(), userController.getSurname()));
                return JsonHTTPCode.CREATED;
        } else { return JsonHTTPCode.BAD_REQUEST_USER_REQUIRE_FIELDS; }
    }
}
