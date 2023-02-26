package com.example.summaryofcandidates.db.service.api;

import com.example.summaryofcandidates.Entity.Skill;
import com.example.summaryofcandidates.db.service.api.request.UpdateSkillRequest;
import org.springframework.lang.Nullable;

import java.util.List;

public interface SkillService {

    @Nullable
    Integer add(Skill skill);

    void update(int id, UpdateSkillRequest request);

    void delete(int id);

    List<Skill> getSkills();

    @Nullable
    Skill get(int id);
}
