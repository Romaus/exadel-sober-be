package com.exadel.sober.repositoriesImplemetation;

import com.exadel.sober.models.Promise;
import com.exadel.sober.models.Reason;
import com.exadel.sober.repositories.PromiseRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.List;

@Repository
public class PromiseRepositoryImpl implements PromiseRepository {
    private final JdbcTemplate jdbcTemplate;

    public PromiseRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public void save(Promise promise) {
        jdbcTemplate.update("insert promise (addiction_id, user_id, expired_date) values (?, ?, ?)",
                promise.getAddictionId(),
                promise.getUserId(),
                promise.getExpiredDate());
    }

    @Override
    public Promise findPromiseByPromiseId(Integer promiseId) {
        return (Promise) jdbcTemplate.queryForObject("select * from promise where promise_id = ?",
                new Object[]{promiseId},
                new BeanPropertyRowMapper(Promise.class));
    }

    @Override
    public List<Promise> findAllByUserId(Integer userId) {
        String sql = "SELECT promise.promise_id, promise.expired_date, addiction.name FROM promise, addiction WHERE promise.addiction_id = addiction.addiction_id and promise.user_id = ?;";
        return jdbcTemplate.query(
                sql,
                new Object[]{userId},
                new BeanPropertyRowMapper(Promise.class));
    }

    @Override
    public List<Reason> findAllReasonsByPromiseId(Integer promiseId) {
        return jdbcTemplate.query("select * from reason where promise_id = ?",
                new Object[]{promiseId},
                new BeanPropertyRowMapper(Reason.class));
    }

    @Override
    public void deletePromiseByPromiseId(Integer promiseId) {
        jdbcTemplate.execute("delete from promise where promise_id ="+promiseId);
    }
}
