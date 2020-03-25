package pl.poloch.imgfighter.controllers;

import org.springframework.http.ResponseEntity;

public class JsonHTTPCode {



public static final ResponseEntity<String> CREATED = ResponseEntity.status(201).body("{\"status\" : \"created\"}");

public static final ResponseEntity<String> BAD_REQUEST_USER_REQUIRE_FIELDS = ResponseEntity.status(400).body("{\"status\" : \"badrequest\", \"reason\" : \"keys: nickname, password, email are required!\"}");

public static final ResponseEntity<String> BAD_REQUEST_USER_EMAIL_OR_NICKNAME_EXIST = ResponseEntity.status(400).body("{\"status\" : \"badrequest\", \"reason\" : \"nickname or email already exist!\"}");

}
