package com.exadel.sober.repositories;

import com.exadel.sober.models.User;
import java.util.List;

public interface UserRepository {
    void save(User newUser);
    User getUserByEmail(String email);
}
