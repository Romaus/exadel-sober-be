package com.exadel.sober.controllers;

import com.exadel.sober.models.User;
import com.exadel.sober.repositories.UserRepository;
import com.exadel.sober.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping(path="/user")
public class UserController {
    private UserRepository userRepository;

    private UserService userService;
    public UserController(UserRepository userRepository,
                          UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping()
    public @ResponseBody List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping()
    public @ResponseBody User addNewUser(@RequestBody User newUser) {
        return userService.addNewUser(newUser);
    }

    @PostMapping(path="/login")
    ResponseEntity<Object> LoginUser(@RequestBody User loginUser) {
        if (userService.existUserByEmail(loginUser.getEmail())) {
            if (userService.login(loginUser) != null) {
                return new ResponseEntity<Object>(
                        userService.login(loginUser),
                        HttpStatus.OK);
            } else {
                return new ResponseEntity<Object>(
                        "You entered wrong credentials",
                        HttpStatus.UNAUTHORIZED);
            }
        } else {
            return new ResponseEntity<Object>(
                    "User with Such Name doesn't exist",
                    HttpStatus.UNAUTHORIZED);
        }


    }

}
