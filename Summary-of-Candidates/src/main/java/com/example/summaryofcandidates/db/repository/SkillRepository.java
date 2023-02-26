package com.example.summaryofcandidates.db.repository;

import com.example.summaryofcandidates.Entity.Skill;
import com.example.summaryofcandidates.db.mapper.SkillRowMapper;
import com.example.summaryofcandidates.db.service.api.request.UpdateSkillRequest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Component
public class SkillRepository {

    private final JdbcTemplate jdbcTemplate;

    private final SkillRowMapper skillRowMapper = new SkillRowMapper();

    public SkillRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Method for adding a skill to the database
    public Integer add(Skill skill) {

        final String sql = "INSERT INTO skills(technology, description) values (?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                ps.setString(1, skill.getTechnology());
                ps.setString(2, skill.getDescription());
                return ps;
            }

        }, keyHolder);
        if (keyHolder.getKey() != null) {
            return keyHolder.getKey().intValue();
        } else {
            return null;
        }
    }

        // Method for updating a skill in the database based on ID
        public void update ( int id, UpdateSkillRequest request){
            final String sql = "UPDATE skills SET description = ? WHERE id = ?";
            jdbcTemplate.update(sql, request.getDescription(), id);
        }

        // Method for deleting a skill in the database based on ID
        public void delete ( int id){
            final String sql = "DELETE FROM skills WHERE id = ?";
            jdbcTemplate.update(sql, id);
        }

        // Method for retrieving a skill from the database based on ID
        public Skill get ( int id){
            final String sql = "SELECT * FROM skills WHERE skills.id = " + id;
            try {
                return jdbcTemplate.queryForObject(sql, skillRowMapper);
            } catch (EmptyResultDataAccessException e) {
                return null;
            }
        }
        // Method for retrieving a list of all skills from the database
        public List<Skill> getSkills () {
            final String sql = "SELECT * FROM skills";
            return jdbcTemplate.query(sql, skillRowMapper);
        }
    }