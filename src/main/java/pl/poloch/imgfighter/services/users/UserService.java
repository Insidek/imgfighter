package pl.poloch.imgfighter.services.users;

import com.google.common.hash.Hashing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.poloch.imgfighter.database_models.UserModel;
import pl.poloch.imgfighter.dto.user.UserAuthRequest;
import pl.poloch.imgfighter.dto.user.UserAuthResponse;
import pl.poloch.imgfighter.dto.user.UserRegisterRequest;
import pl.poloch.imgfighter.repositories.UserRepository;
import pl.poloch.imgfighter.services.general.JWTController;
import pl.poloch.imgfighter.services.general.JsonHTTPCode;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;

import static org.apache.logging.log4j.util.Strings.isBlank;


@Service
public class UserService {
    @Autowired
    UserRepository repository;

    Logger logger = LoggerFactory.getLogger(UserService.class);

    public ResponseEntity<String> register(UserRegisterRequest userRegisterRequest) {
        if (nicknameAndEmailExist(repository, userRegisterRequest)) {
            return JsonHTTPCode.BAD_REQUEST_USER_EMAIL_OR_NICKNAME_EXIST;
        }
        else if (!(isBlank(userRegisterRequest.getNickname()) && isBlank(userRegisterRequest.getEmail()) && isBlank(userRegisterRequest.getPassword()))) {
            repository.save(new UserModel(userRegisterRequest.getNickname(), hashPassword(userRegisterRequest.getPassword()), userRegisterRequest.getEmail(), userRegisterRequest.getName(), userRegisterRequest.getSurname()));
            logger.info("User with the nickname: " + userRegisterRequest.getNickname() + " has been created!");
            return JsonHTTPCode.CREATED;
        } else {
            return JsonHTTPCode.BAD_REQUEST_USER_REQUIRE_FIELDS;
        }
    }

    public ResponseEntity<? extends Serializable> auth(UserAuthRequest userAuthRequest) {
        if (!(isBlank(userAuthRequest.getNickname()) && isBlank(userAuthRequest.getPassword()))) {
            return JsonHTTPCode.BAD_REQUEST_USER_REQUIRE_NICKNAME_AND_PASSWORD;
        }
        if (!(existUser(userAuthRequest))) {
            return JsonHTTPCode.BAD_REQUEST_USER_USER_NOT_EXIST;
        }
        if (verifyPassword(userAuthRequest)) {
            UserAuthResponse userAuthResponse = new UserAuthResponse();
            userAuthResponse.setToken(JWTController.createJWT(userAuthRequest.getNickname()));
            logger.info("User with the nickname: " + userAuthRequest.getNickname() + " has been authorized!");
            return JsonHTTPCode.OK_BODY(userAuthResponse);
        } else {
            return JsonHTTPCode.BAD_REQUEST_USER_WRONG_PASSWORD;
        }
    }

    private Boolean nicknameAndEmailExist(UserRepository repository, UserRegisterRequest userRegisterRequest){
        return ((repository.findByEmail(userRegisterRequest.getEmail()).size() > 0) || (repository.findByNickname(userRegisterRequest.getNickname()).size() > 0));
    }

    private String hashPassword(String password) {
        return Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();
    }

    private Boolean existUser(UserAuthRequest userAuthRequest) {
        return (repository.findByNickname(userAuthRequest.getNickname()).size() > 0);
    }

    private Boolean verifyPassword(UserAuthRequest userAuthRequest) {
        return (repository.findByNickname(userAuthRequest.getNickname()).get(0).getPassword().equals(hashPassword(userAuthRequest.getPassword())));
    }

}
