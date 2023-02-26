package com.example.summaryofcandidates.db.service.api;

import com.example.summaryofcandidates.Entity.CandidateSkill;
import com.example.summaryofcandidates.db.service.api.request.UpdateCandidateSkillRequest;
import org.springframework.lang.Nullable;

import java.util.List;

public interface CandidateSkillService {

    Integer add(CandidateSkill candidateSkill);

    void update(int id, UpdateCandidateSkillRequest request);

    void delete(int id);

    @Nullable
    CandidateSkill get(int id);

    List<CandidateSkill> getCandidatesWithSkill();

    List<CandidateSkill> findByTechnology(String technology);

}
