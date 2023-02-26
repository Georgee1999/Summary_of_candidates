package com.example.summaryofcandidates.db.mapper;

import com.example.summaryofcandidates.Entity.Candidate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CandidateRowMapper implements RowMapper<Candidate> {


    /**
     * @return The Candidate object created from the row data.
     */
    @Override
    public Candidate mapRow(ResultSet rs, int rowNum) throws SQLException {
        Candidate candidate = new Candidate();
        candidate.setId(rs.getInt("id"));
        candidate.setFirstName(rs.getString("first_name"));
        candidate.setLastName(rs.getString("last_name"));
        candidate.setEmail(rs.getString("email"));
        candidate.setPhoneNumber(rs.getString("phoneNumber"));
        return candidate;
    }
}
