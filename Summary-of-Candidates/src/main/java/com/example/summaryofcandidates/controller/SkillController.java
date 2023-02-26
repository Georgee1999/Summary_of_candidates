package com.example.summaryofcandidates.controller;

import com.example.summaryofcandidates.Entity.Skill;
import com.example.summaryofcandidates.db.service.api.SkillService;
import com.example.summaryofcandidates.db.service.api.request.UpdateSkillRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/skill")
public class SkillController {


    private final SkillService skillService;

    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }


    // Add a new skill
    @PostMapping("/add")
    public ResponseEntity add(@RequestBody Skill skill) {
        Integer id = skillService.add(skill);
        if (id != null) {
            return new ResponseEntity(id, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Update an existing skill
    @PatchMapping("/update/{id}")
    public ResponseEntity update(@PathVariable int id, @RequestBody UpdateSkillRequest request) {
        if (skillService.get(id) != null) {
            skillService.update(id, request);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity
                    .status(HttpStatus.PRECONDITION_FAILED)
                    .body("Skill with id " + id + " doesn't exist");
        }
    }

    // Delete a skill
    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable int id) {
        if (skillService.get(id) != null) {
            skillService.delete(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity
                    .status(HttpStatus.PRECONDITION_FAILED)
                    .body("Skill with id " + " doesn't exist");
        }
    }

    // Get a skill by ID
    @GetMapping("/get/{id}")
    public ResponseEntity get(@PathVariable int id) {
        Skill skill = skillService.get(id);
        if (skill != null) {
            return new ResponseEntity<>(skill, HttpStatus.OK);
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Skill with id " + id + " doesn't exist");

        }
    }

    // Get all skills
    @GetMapping("/getAll")
    public ResponseEntity getSkills() {
        List<Skill> skills = skillService.getSkills();
        return new ResponseEntity<>(skills, HttpStatus.OK);
    }
}
