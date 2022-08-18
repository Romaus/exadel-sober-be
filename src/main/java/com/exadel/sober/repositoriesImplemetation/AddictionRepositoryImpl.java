package com.exadel.sober.repositoriesImplemetation;

import com.exadel.sober.models.Addiction;
import com.exadel.sober.repositories.AddictionRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AddictionRepositoryImpl implements AddictionRepository {

    private final JdbcTemplate jdbcTemplate;

    public AddictionRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public List<Addiction> findAll() {
        return jdbcTemplate.query("select * from addiction", new BeanPropertyRowMapper(Addiction.class));
    }
    @Override
    public void save(Addiction addiction) {
        jdbcTemplate.update("insert addiction (name, description) values (?, ?)",
                addiction.getName(),
                addiction.getDescription());
    }
}
