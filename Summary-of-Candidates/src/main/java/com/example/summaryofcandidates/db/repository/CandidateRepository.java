package com.example.summaryofcandidates.db.repository;

import com.example.summaryofcandidates.Entity.Candidate;
import com.example.summaryofcandidates.db.mapper.CandidateRowMapper;
import com.example.summaryofcandidates.db.service.api.request.UpdateCandidateRequest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.List;

@Component
public class CandidateRepository {

    public final JdbcTemplate jdbcTemplate;

    public final CandidateRowMapper candidateRowMapper = new CandidateRowMapper();


    public CandidateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Method for adding a candidate to the database
    public Integer add(Candidate candidate){

        final String sql = "INSERT INTO candidates(first_name,last_name,email,phoneNumber) values (?,?,?,?)";

        // Object to hold the generated key
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1,candidate.getFirstName());
                ps.setString(2,candidate.getLastName());
                ps.setString(3,candidate.getEmail());
                if (candidate.getPhoneNumber() != null){
                    ps.setString(4,candidate.getPhoneNumber());
                }else {
                    ps.setNull(4, Types.INTEGER);
                }
                return ps;
            }
        },keyHolder);

        // Return the generated key as an Integer
        if (keyHolder.getKey() != null){
            return keyHolder.getKey().intValue();
        }else {
            return null;
        }
    }

    // Method for updating a candidate in the database based on ID
    public void update(int id, UpdateCandidateRequest request){
        final String sql = "UPDATE candidates SET first_name = ?, last_name = ?, email = ?, phoneNumber = ? WHERE id = ?";
        jdbcTemplate.update(sql, request.getFirstName(), request.getLastName(), request.getEmail(), request.getPhoneNumber(), id);
    }

    // Method for deleting a candidate from the database based on ID
    public void delete(int id){
        final String sql = "DELETE FROM candidates WHERE id= ?";
        jdbcTemplate.update(sql,id);
    }

    // Method for retrieving a single candidate from the database based on ID
    public Candidate get(int id){
        final String sql = "SELECT * FROM candidates WHERE candidates.id =" + id ;
        try{
            return jdbcTemplate.queryForObject(sql, candidateRowMapper);
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    // Method for retrieving a list of all candidates from the database
    public List<Candidate> getCandidates(){
        final String sql = "SELECT * FROM candidates";
        return jdbcTemplate.query(sql, candidateRowMapper);
    }
}
