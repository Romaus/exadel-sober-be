package com.exadel.sober.repositories;

import com.exadel.sober.models.Promise;
import com.exadel.sober.models.Reason;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.util.List;

public interface PromiseRepository {
    void save(Promise promise);
    Promise findPromiseByPromiseId(Integer promiseId);
    List<Promise> findAllByUserId(Integer userId);
    List<Reason> findAllReasonsByPromiseId(Integer promiseId);
    void deletePromiseByPromiseId(Integer promiseId);
}
