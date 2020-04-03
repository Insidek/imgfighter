package pl.poloch.imgfighter.controllers.general;

import org.springframework.http.ResponseEntity;
import pl.poloch.imgfighter.controllers.users.UserAuth;

import java.io.Serializable;

public class JsonHTTPCode {

    /* HTTP CODE 200 - OK */

    public static ResponseEntity<? extends Serializable> OK_BODY(Serializable json){
        return ResponseEntity.status(200).body(json);
    }

    /* HTTP CODE 201 - CREATED */
public static final ResponseEntity<String> CREATED = ResponseEntity.status(201).body("{\"status\" : \"created\"}");


    /* HTTP CODE 404 - BADREQUEST */
public static final ResponseEntity<String> BAD_REQUEST_USER_REQUIRE_FIELDS = ResponseEntity.status(400).body("{\"status\" : \"badrequest\", \"reason\" : \"keys: nickname, password, email are required!\"}");

public static final ResponseEntity<String> BAD_REQUEST_USER_USER_NOT_EXIST = ResponseEntity.status(400).body("{\"status\" : \"badrequest\", \"reason\" : \"user not exist!\"}");

public static final ResponseEntity<String> BAD_REQUEST_USER_WRONG_PASSWORD = ResponseEntity.status(400).body("{\"status\" : \"badrequest\", \"reason\" : \"wrong password!\"}");

public static final ResponseEntity<String> BAD_REQUEST_USER_EMAIL_OR_NICKNAME_EXIST = ResponseEntity.status(400).body("{\"status\" : \"badrequest\", \"reason\" : \"nickname or email already exist!\"}");

public static final ResponseEntity<String> BAD_REQUEST_USER_REQUIRE_NICKNAME_AND_PASSWORD = ResponseEntity.status(400).body("{\"status\" : \"badrequest\", \"reason\" : \"keys: nickname and password are required!\"}");

}
