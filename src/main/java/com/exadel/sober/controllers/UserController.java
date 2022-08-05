package com.exadel.sober.controllers;

import com.exadel.sober.models.User;
import com.exadel.sober.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping(path="/user")
public class UserController {
    private UserService userService;
    public UserController( UserService userService ) {
        this.userService = userService;
    }

    @PostMapping()
    ResponseEntity<?> addNewUser(@RequestBody User newUser) {
        return new ResponseEntity<User>(
                userService.addNewUser(newUser),
                HttpStatus.OK);
    }
    @PostMapping(path="/login")
    ResponseEntity<?> LoginUser(@RequestBody User loginUser) {
        return new ResponseEntity<User>(
                userService.login(loginUser),
                HttpStatus.OK);
    };
}
