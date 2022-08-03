package com.exadel.sober.repositories;

import com.exadel.sober.models.Promise;
import com.exadel.sober.models.Reason;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.util.List;

public interface PromiseRepository {
    RowMapper<Promise> ROW_MAPPER_PROMISE = (ResultSet resultSet, int rowNum) -> {
        Promise promise = new Promise();
        promise.setExpiredDate(resultSet.getTimestamp("promise.expired_date"));
        promise.setPromiseId(resultSet.getInt("promise.promise_id"));
        promise.setName(resultSet.getString("addiction.name"));
        return promise;
    };

    Promise save(Promise promise);
    List<Promise> findAllByUserId(Integer userId);
    List<Reason> findAllReasonsByPromiseId(Integer promiseId);
}
