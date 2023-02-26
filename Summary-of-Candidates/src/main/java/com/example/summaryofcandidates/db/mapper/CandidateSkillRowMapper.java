package com.example.summaryofcandidates.db.mapper;

import com.example.summaryofcandidates.Entity.CandidateSkill;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CandidateSkillRowMapper implements RowMapper<CandidateSkill> {


    @Override
    public CandidateSkill mapRow(ResultSet rs, int rowNum) throws SQLException {
        CandidateSkill candidateSkill = new CandidateSkill();
        candidateSkill.setId(rs.getInt("id"));
        candidateSkill.setCandidateId(rs.getInt("candidate_id"));
        candidateSkill.setSkillId(rs.getInt("skill_id"));
        candidateSkill.setRating(rs.getInt("rating"));
        return candidateSkill;
    }
}
