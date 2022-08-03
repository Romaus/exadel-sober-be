package com.exadel.sober.services;

import com.exadel.sober.models.User;
import com.exadel.sober.repositories.PromiseRepository;
import com.exadel.sober.repositories.UserRepository;
import com.exadel.sober.repositoriesImplemetation.AddictionRepositoryImpl;
import org.springframework.dao.EmptyResultDataAccessException;
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
        User userForSave = newUser;
        userForSave.setPassword(get_SHA_512_SecurePassword(newUser.getPassword()));
        userRepository.save(userForSave);
        userForSave.setPassword(null);
        return userForSave;
    }

    public User login(User loginUser) {

        if (checkCredential(loginUser)){
            User userForResponse = userRepository.getUserByEmail(loginUser.getEmail());
            userForResponse.setPassword(null);
            userForResponse.setPromises(promiseService.getPromisesForClient(userForResponse.getUserId()));
            return userForResponse;
        }
        else {
            return null;
        }
    }

    public boolean existUserByEmail(String email) {
        if (userRepository.getUserByEmail(email) != null) {
           return true;
        } else {
            return false;
        }
    }

    private boolean checkCredential(User loginUser) {
        if (!existUserByEmail(loginUser.getEmail())) {
            return false;
        }
        User user = userRepository.getUserByEmail(loginUser.getEmail());
        if (Objects.equals(user.getEmail(), loginUser.getEmail())
                && Objects.equals(user.getPassword(), get_SHA_512_SecurePassword(loginUser.getPassword()))) {
            return true;
        } else {
            return false;
        }
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
