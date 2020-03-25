package pl.poloch.imgfighter.api.pub;

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

    @Autowired
    UserRepository repository;

    @PostMapping(value="/api/public/register", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> register(@RequestBody UserController userController) {
        repository.save(new UserModel(userController.getNickname(), userController.getPassword(), userController.getEmail(), userController.getName(), userController.getSurname()));
        return JsonHTTPCode.CREATED;
    }

}
