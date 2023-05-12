package org.geekhub.denis.controller;

import lombok.RequiredArgsConstructor;
import org.geekhub.denis.entity.UserEntity;
import org.geekhub.denis.model.*;
import org.geekhub.denis.service.AuthenticationService;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


/**
 * @author Apilat Denis
 * Date :29.04.2023
 * Time :22:05
 * Project Name :gh-hw-denis-apilat
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<JwtResponseModel> register(@RequestBody RegisterRequestModel request){
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<JwtResponseModel> authenticate(@RequestBody AuthenticationRequestModel request){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @GetMapping("/profile")
    public ResponseEntity<CurrentUserModel> profile() {
        return ResponseEntity.ok(authenticationService.getCurrentUser());
    }

}
