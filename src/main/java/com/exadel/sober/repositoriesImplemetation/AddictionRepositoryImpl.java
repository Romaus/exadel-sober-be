package com.exadel.sober.repositoriesImplemetation;

import com.exadel.sober.models.Addiction;
import com.exadel.sober.repositories.AddictionRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
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
    public Addiction save(Addiction addiction) {
        String name = addiction.getName();
        String description = addiction.getDescription();
        int addiction_id = jdbcTemplate.update("insert addiction (name, description) values (?, ?)", name, description);
        return jdbcTemplate.queryForObject("select * from addiction where addiction_id = ?", new Object[]{addiction_id}, Addiction.class);
    }
    @Override
    public Addiction findById(Integer addiction_id) {
        return jdbcTemplate.queryForObject("select * from addiction where addiction_id = ?", new Object[]{addiction_id}, Addiction.class);
    }
}
