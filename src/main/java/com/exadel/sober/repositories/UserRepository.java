package com.exadel.sober.repositories;

import com.exadel.sober.models.User;
import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findByName(String name);
    boolean existsByName(String name);
}
