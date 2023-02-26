package com.example.summaryofcandidates.db.service.impl;

import com.example.summaryofcandidates.Entity.Skill;
import com.example.summaryofcandidates.db.repository.SkillRepository;
import com.example.summaryofcandidates.db.service.api.SkillService;
import com.example.summaryofcandidates.db.service.api.request.UpdateSkillRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillServiceImpl implements SkillService {

    public final SkillRepository skillRepository;

    public SkillServiceImpl(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    @Override
    public Integer add(Skill skill) {
        return skillRepository.add(skill);
    }

    @Override
    public void update(int id, UpdateSkillRequest request) {
        skillRepository.update(id,request);
    }

    @Override
    public void delete(int id) {
        skillRepository.delete(id);
    }

    @Override
    public List<Skill> getSkills() {
        return skillRepository.getSkills();
    }

    @Override
    public Skill get(int id) {
        return skillRepository.get(id);
    }
}
