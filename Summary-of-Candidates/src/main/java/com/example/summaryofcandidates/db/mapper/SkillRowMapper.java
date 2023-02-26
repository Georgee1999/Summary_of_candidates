package com.example.summaryofcandidates.db.mapper;

import com.example.summaryofcandidates.Entity.Skill;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SkillRowMapper implements RowMapper<Skill> {


    /**
     * @return The Skill object created from the row data.
     */
    @Override
    public Skill mapRow(ResultSet rs, int rowNum) throws SQLException {
        Skill skill = new Skill();
        skill.setId(rs.getInt("id"));
        skill.setTechnology(rs.getString("technology"));
        skill.setDescription(rs.getString("description"));
        return skill;
    }
}
