package com.exadel.sober.repositoriesImplemetation;

import com.exadel.sober.models.Reason;
import com.exadel.sober.repositories.ReasonRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ReasonRepositoryImpl implements ReasonRepository {

    private final JdbcTemplate jdbcTemplate;

    public ReasonRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public void save(Reason reason) {
        Integer promiseId = reason.getPromiseId();
        String description = reason.getDescription();
        jdbcTemplate.update("insert reason (promise_id, description) values (?, ?)", promiseId, description);
    }
}
