package pl.poloch.imgfighter.controllers;

import org.springframework.http.ResponseEntity;

public class JsonHTTPCode {

public static final ResponseEntity<String> CREATED = ResponseEntity.status(201).body("{\"status\" : \"created\"}");

}
