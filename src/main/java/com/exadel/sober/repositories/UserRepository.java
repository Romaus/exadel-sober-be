package com.exadel.sober.repositories;

import com.exadel.sober.models.User;
import java.util.List;

public interface UserRepository {

    List<User> findAll();
    User save(User newUser);
    User getUserByEmail(String email);
}
