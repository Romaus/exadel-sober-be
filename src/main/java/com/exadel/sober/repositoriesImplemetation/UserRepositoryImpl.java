package com.exadel.sober.repositoriesImplemetation;

import com.exadel.sober.models.User;
import com.exadel.sober.repositories.UserRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public void save(User newUser) {
        jdbcTemplate.update("insert user (name, email, password) values (?, ?, ?)",
                newUser.getName(),
                newUser.getEmail(),
                newUser.getPassword());
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
