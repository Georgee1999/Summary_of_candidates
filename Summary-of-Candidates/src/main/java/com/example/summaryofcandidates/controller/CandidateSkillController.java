package com.example.summaryofcandidates.controller;

import com.example.summaryofcandidates.Entity.CandidateSkill;
import com.example.summaryofcandidates.db.service.api.CandidateSkillService;
import com.example.summaryofcandidates.db.service.api.request.UpdateCandidateSkillRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/candidateSkills")
public class CandidateSkillController {

    private final CandidateSkillService candidateSkillService;

    public CandidateSkillController(CandidateSkillService candidateSkillService) {
        this.candidateSkillService = candidateSkillService;
    }

    // Add a new candidate skill
    @PostMapping("/add")
    public ResponseEntity<?> addCandidateSkill(@RequestBody CandidateSkill candidateSkill) {
        try {
            Integer id = candidateSkillService.add(candidateSkill);
            candidateSkill.setId(id);
            return new ResponseEntity<>(candidateSkill, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Get a candidate skill based on ID
    @GetMapping("/{id}")
    public ResponseEntity<CandidateSkill> getCandidateSkill(@PathVariable Integer id) {
        CandidateSkill candidateSkill = candidateSkillService.get(id);
        if (candidateSkill == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(candidateSkill, HttpStatus.OK);
    }

    // Get a candidate skill list
    @GetMapping
    public List<CandidateSkill> getAllCandidateSkills() {
        return candidateSkillService.getCandidatesWithSkill();
    }

    // Update existing candidate skill
    @PatchMapping("/update/{id}")
    public ResponseEntity update(@PathVariable int id, @RequestBody UpdateCandidateSkillRequest request) {
        if (candidateSkillService.get(id) != null) {
            candidateSkillService.update(id, request);
            return ResponseEntity.ok().build();
        } else {
            return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
        }
    }

    // Delete existing candidate skill based on ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCandidateSkill(@PathVariable Integer id) {
        candidateSkillService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}

