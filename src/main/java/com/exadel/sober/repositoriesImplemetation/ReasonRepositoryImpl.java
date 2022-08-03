package com.exadel.sober.repositoriesImplemetation;

import com.exadel.sober.models.Reason;
import com.exadel.sober.repositories.ReasonRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public final class ReasonRepositoryImpl implements ReasonRepository {


    private final JdbcTemplate jdbcTemplate;

    public ReasonRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Reason> findAllByPromiseId(Integer id) {
        return jdbcTemplate.query("select * from reason where promise_id = ?", new Object[]{id}, new BeanPropertyRowMapper(Reason.class));
    }

    @Override
    public Reason save(Reason reason) {
        Integer promiseId = reason.getPromiseId();
        String description = reason.getDescription();
        int reason_id = jdbcTemplate.update("insert reason (promise_id, description) values (?, ?)", promiseId, description);
        return (Reason) jdbcTemplate.queryForObject("select * from reason where reason_id = ?", new Object[]{reason_id}, new BeanPropertyRowMapper(Reason.class));
    }
}
