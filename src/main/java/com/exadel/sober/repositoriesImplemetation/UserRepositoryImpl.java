package com.exadel.sober.repositoriesImplemetation;

import com.exadel.sober.models.User;
import com.exadel.sober.repositories.UserRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserRepositoryImpl implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> findAll() {
        String sql = "select * from user ";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper(User.class));
    }

    @Override
    public User save(User newUser) {
        String name = newUser.getName();
        String email = newUser.getEmail();
        String password = newUser.getPassword();
        int user_id = jdbcTemplate.update("insert user (name, email, password) values (?, ?, ?)", name, email, password);
        return null;
    }

    @Override
    public User getUserByEmail(String email) throws EmptyResultDataAccessException {
        String sql = "select * from user where email = ?";
        try {
            return (User) jdbcTemplate.queryForObject(sql, new Object[]{email}, new BeanPropertyRowMapper(User.class));
        }
        catch (EmptyResultDataAccessException exception) {
            return null;
        }

    }
}
