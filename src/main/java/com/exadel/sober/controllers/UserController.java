package com.exadel.sober.controllers;

import com.exadel.sober.models.Addiction;
import com.exadel.sober.models.Promise;
import com.exadel.sober.repositories.AddictionRepository;
import com.exadel.sober.models.User;
import com.exadel.sober.repositories.PromiseRepository;
import com.exadel.sober.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;


@RestController
@CrossOrigin
@RequestMapping(path="/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AddictionRepository addictionRepository;

    @Autowired
    private PromiseRepository promiseRepository;

    @PostMapping(path="/add")
    public @ResponseBody String addNewUser(@RequestBody User newUser) {
        User userForSave = newUser;
        userForSave.setPassword(get_SHA_512_SecurePassword(newUser.getPassword()));
        userRepository.save(newUser);
        return "User " +newUser.getName()+ " registration successful";
    }

    @PostMapping(path="/login")
    public @ResponseBody String LoginUser(@RequestBody User loginUser) {
        boolean valid = false;
        if (userRepository.existsByName(loginUser.getName())) {
            User user = userRepository.findByName(loginUser.getName());
            if (Objects.equals(user.getName(), loginUser.getName())
                    && Objects.equals(user.getPassword(), get_SHA_512_SecurePassword(loginUser.getPassword()))) {
              valid = true;
            }
        }
        if (valid) {
            return loginUser.getName();
        }
        else {
            return "credential invalid";
        }
    }

    @PostMapping(path="/delete")
    public @ResponseBody String deleteNewUser(@RequestParam String name) {
        //User n = userRepository.findByName(name);
        //userRepository.delete(n);
        return "User " +name+ " was deleted";
    }

    @PostMapping(path="/addPromise")
    public @ResponseBody List<Promise> addPromiseByUserName (@RequestParam String userName, @RequestParam Integer addiction_id, @RequestParam Integer days) {
        User user = userRepository.findByName(userName);
        Addiction addiction = addictionRepository.findById(addiction_id).get();
        Integer d = days;

        Promise p = new Promise();
        p.setAddictionId(addiction.getAddictionId());
        p.setUserId(user.getUserId());
        p.setName("i will not being take " + addiction.getName());
        p.setExpiredDate(new Timestamp(System.currentTimeMillis() + d*24*3600*1000));
        promiseRepository.save(p);
        return userRepository.findByName(userName).getPromises();
    }

    @GetMapping(path="/getPromises")
    public @ResponseBody List<Promise> getPromiseByUserName (@RequestParam String userName) {

        User user = userRepository.findByName(userName);
        return user.getPromises();
    }

    @PostMapping(path="/deletePromise")
    public @ResponseBody void deletePromiseByUserName (@RequestParam String name) {

        User user = userRepository.findByName(name);
        user.getPromises().clear();
        userRepository.save(user);
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    private String get_SHA_512_SecurePassword(String passwordToHash){
        String salt = "secureSober";
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt.getBytes(StandardCharsets.UTF_8));
            byte[] bytes = md.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++){
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }
}
