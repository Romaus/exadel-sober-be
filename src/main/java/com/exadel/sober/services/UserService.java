package com.exadel.sober.services;

import com.exadel.sober.exceptions.BadUserCredentialException;
import com.exadel.sober.exceptions.NoSuchUserExistsException;
import com.exadel.sober.exceptions.UserAlreadyExistsException;
import com.exadel.sober.models.User;
import com.exadel.sober.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

@Service
public class UserService {

    private UserRepository userRepository;
    private PromiseService promiseService;

    public UserService(UserRepository userRepository,
                       PromiseService promiseService) {
        this.userRepository = userRepository;
        this.promiseService = promiseService;
    }

    public User addNewUser(User newUser) {
        if (this.existUserByEmail(newUser.getEmail()) != null) {
            throw new UserAlreadyExistsException("User with such e-mail has already registered");
        }
        User userForSave = newUser;
        userForSave.setPassword(get_SHA_512_SecurePassword(newUser.getPassword()));
        userRepository.save(userForSave);
        userForSave.setPassword(null);
        return userForSave;
    }
    public User login(User loginUser) {
        User userDB = this.existUserByEmail(loginUser.getEmail());
        if (userDB == null) {
            throw new NoSuchUserExistsException("User with such e-mail doesn\'t exist");
        } else if(!checkCredential(loginUser, userDB)) {
            throw new BadUserCredentialException("You entered wrong credentials");
        } else {
            userDB.setPassword(null);
            userDB.setPromises(promiseService.getPromisesForClient(userDB.getUserId()));
            return userDB;
        }
    }
    public User existUserByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }

    private boolean checkCredential(User loginUser, User userDB) {
        return (Objects.equals(userDB.getEmail(), loginUser.getEmail())
                && Objects.equals(userDB.getPassword(), get_SHA_512_SecurePassword(loginUser.getPassword())));
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
