package com.exadel.sober.repositoriesImplemetation;

import com.exadel.sober.models.Promise;
import com.exadel.sober.models.Reason;
import com.exadel.sober.repositories.PromiseRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;

@Component
public class PromiseRepositoryImpl implements PromiseRepository {

    private final JdbcTemplate jdbcTemplate;

    public PromiseRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Promise save(Promise promise) {
        Integer addiction_id = promise.getAddictionId();
        Integer user_id = promise.getUserId();
        Timestamp expired_date = promise.getExpiredDate();
        int promise_id = jdbcTemplate.update("insert promise (addiction_id, user_id, expired_date) values (?, ?, ?)", addiction_id, user_id, expired_date);
        return null;
    }

    @Override
    public List<Promise> findAllByUserId(Integer userId) {
        String sql = "SELECT promise.promise_id, promise.expired_date, addiction.name, addiction.description FROM promise, addiction WHERE promise.addiction_id = addiction.addiction_id and promise.user_id = ?;";
        return jdbcTemplate.query(sql, new Object[]{userId}, ROW_MAPPER_PROMISE);
    }

    @Override
    public List<Reason> findAllReasonsByPromiseId(Integer promiseId) {
        String sql = "select * from reason where promise_id = ?";
        return jdbcTemplate.query(
                sql,
                new Object[]{promiseId},
                new BeanPropertyRowMapper(Reason.class));
    }
}
