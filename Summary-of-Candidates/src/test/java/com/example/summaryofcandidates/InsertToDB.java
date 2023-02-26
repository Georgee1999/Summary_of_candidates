package com.example.summaryofcandidates;

import com.example.summaryofcandidates.Entity.Candidate;
import com.example.summaryofcandidates.Entity.CandidateSkill;
import com.example.summaryofcandidates.Entity.Skill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class InsertToDB {

    @Autowired
    JdbcTemplate jdbcTemplate = new JdbcTemplate();

    private final String insertCandidate = "INSERT INTO candidates (first_name,last_name,email,phoneNumber) values (?,?,?,?)";

    private final String insertSkill= "INSERT INTO skills (technology, description) values (?,?)";

    private final String insertCandidateSkill = "INSERT INTO candidate_skill (candidate_id, skill_id, rating) VALUES (?, ?, ?)";

    @Test
    public void createSkill(){
        Skill skill = new Skill();
        skill.setTechnology("Java");
        skill.setDescription("xxx");

        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(insertSkill);
                ps.setString(1,skill.getTechnology());
                ps.setString(2,skill.getTechnology());
                return ps;
            }
        });
    }

    @Test
    public void createCandidate(){
        Candidate candidate = new Candidate();
        candidate.setFirstName("Jirka");
        candidate.setLastName("Kvarda");
        candidate.setEmail("xxx");
        candidate.setPhoneNumber("xxx");

        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(insertCandidate);
                ps.setString(1, candidate.getFirstName());
                ps.setString(2, candidate.getLastName());
                ps.setString(3, candidate.getEmail());
                ps.setString(4, candidate.getPhoneNumber());
                return ps;
            }
        });
    }

    @Test
    public void createCandidateSkill() {
        CandidateSkill candidateSkill = new CandidateSkill();
        candidateSkill.setCandidateId(1);
        candidateSkill.setSkillId(1);
        candidateSkill.setRating(7);

        int result = jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(insertCandidateSkill);
                ps.setInt(1, candidateSkill.getCandidateId());
                ps.setInt(2, candidateSkill.getSkillId());
                ps.setInt(3, candidateSkill.getRating());
                return ps;
            }
        });
    }
}
