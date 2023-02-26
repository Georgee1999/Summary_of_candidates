package com.example.summaryofcandidates.controller;

import com.example.summaryofcandidates.Entity.Candidate;
import com.example.summaryofcandidates.Entity.CandidateSkill;
import com.example.summaryofcandidates.db.service.api.CandidateService;
import com.example.summaryofcandidates.db.service.api.CandidateSkillService;
import com.example.summaryofcandidates.db.service.api.request.UpdateCandidateRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    private final CandidateService candidateService;
    private final CandidateSkillService candidateSkillService;

    public CandidateController(CandidateService candidateService, CandidateSkillService candidateSkillService) {
        this.candidateService = candidateService;
        this.candidateSkillService = candidateSkillService;
    }

    // Add a new candidate
    @PostMapping("/add")
    public ResponseEntity add(@RequestBody Candidate candidate){
        Integer id = candidateService.add(candidate);
        if (id != null){
            return new ResponseEntity<>(id, HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Update an existing Candidate
    @PatchMapping("/update/{id}")
    public ResponseEntity update(@PathVariable int id, @RequestBody UpdateCandidateRequest request) {
        if (candidateService.get(id) != null) {
            candidateService.update(id, request);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity
                    .status(HttpStatus.PRECONDITION_FAILED)
                    .body("Candidate with ID " + id + " doesn't exist");
        }
    }

    // Get a candidate by ID
    @GetMapping("/get/{id}")
    public ResponseEntity get(@PathVariable int id) {
        Candidate candidate = candidateService.get(id);
        if (candidate != null) {
            return new ResponseEntity<>(candidate, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Get all candidates
    @GetMapping("/getAll")
    public ResponseEntity getCandidates() {
        List<Candidate> candidates = candidateService.getCandidates();
        if (!candidates.isEmpty()){
            return new ResponseEntity<>(candidates, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.NOT_FOUND);
        }
    }

    // Delete an existing candidate
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteProduct(@PathVariable("id") int id){
        if(candidateService.get(id) != null){
            candidateService.delete(id);
            return ResponseEntity.ok().build();
        }else {
            return  ResponseEntity
                    .status(HttpStatus.PRECONDITION_FAILED)
                    .body("Candidate with id: " + id + " does not exist");
        }
    }

    // Find a list of candidates based on technology
    @GetMapping("/{technology}")
    public ResponseEntity findByTechnology(@PathVariable("technology") String technology) {
        List<CandidateSkill> candidates = candidateSkillService.findByTechnology(technology);
        if (candidates.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(candidates);
    }
}
