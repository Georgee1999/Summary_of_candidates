package com.example.summaryofcandidates.db.service.impl;

import com.example.summaryofcandidates.Entity.CandidateSkill;
import com.example.summaryofcandidates.db.repository.CandidateSkillRepository;
import com.example.summaryofcandidates.db.service.api.CandidateSkillService;
import com.example.summaryofcandidates.db.service.api.request.UpdateCandidateSkillRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidateSkillServiceImpl implements CandidateSkillService {

    public final CandidateSkillRepository candidateSkillRepository;

    public CandidateSkillServiceImpl(CandidateSkillRepository candidateSkillRepository) {
        this.candidateSkillRepository = candidateSkillRepository;
    }


    @Override
    public Integer add(CandidateSkill candidateSkill) {
        if (candidateSkill.getRating() < 1 || candidateSkill.getRating() > 10) {
            throw new IllegalArgumentException("Rating must be between 1 and 10.");
        }else {
            return candidateSkillRepository.add(candidateSkill);
        }
    }

    @Override
    public void update(int id, UpdateCandidateSkillRequest request) {
        candidateSkillRepository.update(id, request);
    }

    @Override
    public void delete(int id) {
        candidateSkillRepository.delete(id);
    }

    @Override
    public CandidateSkill get(int id) {
        return candidateSkillRepository.get(id);
    }

    @Override
    public List<CandidateSkill> getCandidatesWithSkill() {
        return candidateSkillRepository.getCandidatesWithSkill();
    }

    @Override
    public List<CandidateSkill> findByTechnology(String technology) {
        return candidateSkillRepository.findByTechnology(technology);
    }
}
