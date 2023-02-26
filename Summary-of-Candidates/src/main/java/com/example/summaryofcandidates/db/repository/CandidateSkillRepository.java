package com.example.summaryofcandidates.db.repository;

import com.example.summaryofcandidates.Entity.Candidate;
import com.example.summaryofcandidates.Entity.CandidateSkill;
import com.example.summaryofcandidates.db.mapper.CandidateSkillRowMapper;
import com.example.summaryofcandidates.db.service.api.request.UpdateCandidateRequest;
import com.example.summaryofcandidates.db.service.api.request.UpdateCandidateSkillRequest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.List;

@Component
public class CandidateSkillRepository {

    private final JdbcTemplate jdbcTemplate;

    private final CandidateSkillRowMapper candidateSkillRowMapper = new CandidateSkillRowMapper();

    public CandidateSkillRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Add a new candidate skill record to the database
    public Integer add(CandidateSkill candidateSkill){
        final String sql = "INSERT INTO candidate_skill(candidate_id, skill_id, rating) values (?,?,?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1,candidateSkill.getCandidateId());
                ps.setInt(2,candidateSkill.getSkillId());
                ps.setInt(3,candidateSkill.getRating());
                return ps;
            }
        },keyHolder);

        if (keyHolder.getKey() != null){
            return keyHolder.getKey().intValue();
        }else {
            return null;
        }
    }
    // Method for updating the rating of an existing candidate skill record in the database
    public void update(int id, UpdateCandidateSkillRequest request){
        final String sql = "UPDATE candidate_skill SET rating = ? WHERE id = ?";
        jdbcTemplate.update(sql, request.getRating(), id);
    }

    // Method for deleting an existing candidate skill record from the database based on ID
    public void delete(int id){
        final String sql = "DELETE FROM candidate_skill WHERE id= ?";
        jdbcTemplate.update(sql,id);
    }

    // Method for retrieving a specific candidate skill record from the database based on ID
    public CandidateSkill get(int id){
        final String sql = "SELECT * FROM candidate_skill WHERE id = " + id ;
        try{
            return jdbcTemplate.queryForObject(sql, candidateSkillRowMapper);
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    // Method for retrieving a list of all candidates from the database
    public List<CandidateSkill> getCandidatesWithSkill(){
        final String sql = "SELECT * FROM candidate_skill";
        return jdbcTemplate.query(sql, candidateSkillRowMapper);
    }

    // Method for retrieving a list of all candidates from the database based on technology
    public List<CandidateSkill> findByTechnology(String technology) {
        final String sql = "SELECT cs.id, cs.candidate_id, cs.skill_id, cs.rating " +
                "FROM candidate_skill cs " +
                "JOIN skills s ON s.id = cs.skill_id " +
                "WHERE s.technology = ?";
        return jdbcTemplate.query(sql, candidateSkillRowMapper, technology);
}

}


