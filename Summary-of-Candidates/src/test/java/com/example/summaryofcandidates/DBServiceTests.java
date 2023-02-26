package com.example.summaryofcandidates;

import com.example.summaryofcandidates.Entity.Candidate;
import com.example.summaryofcandidates.Entity.CandidateSkill;
import com.example.summaryofcandidates.Entity.Skill;
import com.example.summaryofcandidates.db.service.api.CandidateService;
import com.example.summaryofcandidates.db.service.api.CandidateSkillService;
import com.example.summaryofcandidates.db.service.api.SkillService;
import com.example.summaryofcandidates.db.service.api.request.UpdateCandidateRequest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class DBServiceTests {

    @Autowired
    private CandidateService candidateService;

    @Autowired
    private SkillService skillService;

    @Autowired
    private CandidateSkillService candidateSkillService;

    @Test
    public void skill(){
        Skill skill = new Skill("Java","xxx");
        Integer id = skillService.add(skill);
        assert id != null;
        skill.setId(id);
    }


    @Test
    public void candidate(){
        Candidate candidate = new Candidate("Jirka","Kvarda","xxx","xxx");
        Integer id = candidateService.add(candidate);
        assert id != null;
        candidate.setId(id);

        Candidate fromDb = candidateService.get(id);
        Assert.assertEquals(candidate,fromDb);

        List<Candidate> candidates = candidateService.getCandidates();
        Assert.assertEquals(1,candidates.size());
        Assert.assertEquals(fromDb,candidates.get(0));

        candidate.setLastName("xxx");
        UpdateCandidateRequest candidateRequest = new UpdateCandidateRequest(candidate.getFirstName(),candidate.getLastName(),candidate.getEmail(),candidate.getPhoneNumber());

        candidateService.update(id,candidateRequest);
        Candidate fromDbAfterUpdate = candidateService.get(id);
        Assert.assertEquals(candidate,fromDbAfterUpdate);
        Assert.assertNotEquals(fromDb,fromDbAfterUpdate);

        candidateService.delete(id);
        Assert.assertEquals(0,candidateService.getCandidates().size());
    }

    @Test
    public void candidateSkill(){
        Skill skill = new Skill("Java","xxx");
        Integer skillId = skillService.add(skill);
        assert skillId != null;
        skill.setId(skillId);

        Candidate candidate = new Candidate("Jirka","Kvarda","xxx","xxx");
        Integer candidateId = candidateService.add(candidate);
        assert candidateId != null;
        candidate.setId(candidateId);

        CandidateSkill candidateSkill = new CandidateSkill(candidate.getId(),skill.getId(),3);
        Integer candidateSkillId = candidateSkillService.add(candidateSkill);
        assert candidateSkillId != null;
        candidateSkill.setId(candidateSkillId);

        CandidateSkill fromDb = candidateSkillService.get(candidateSkillId);
        Assert.assertEquals(candidateSkill, fromDb);

        List<CandidateSkill> candidateSkills = candidateSkillService.getCandidatesWithSkill();
        Assert.assertEquals(1, candidateSkills.size());
        Assert.assertEquals(fromDb, candidateSkills.get(0));

        candidateSkillService.delete(candidateSkillId);
        Assert.assertEquals(0, candidateSkillService.getCandidatesWithSkill().size());
    }

}
