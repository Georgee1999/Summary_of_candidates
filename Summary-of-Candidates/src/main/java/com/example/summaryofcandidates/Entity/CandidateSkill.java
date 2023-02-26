package com.example.summaryofcandidates.Entity;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.Objects;

public class CandidateSkill {

    @Nullable
    private Integer id;

    @NonNull
    private int candidateId;
    @NonNull
    private int skillId;
    @NonNull
    private int rating;

    public CandidateSkill(){}

    public CandidateSkill(int candidateId, int skillId, int rating) {
        this.candidateId = candidateId;
        this.skillId = skillId;
        this.rating = rating;
    }

    @Nullable
    public Integer getId() {
        return id;
    }

    public void setId(@Nullable Integer id) {
        this.id = id;
    }

    public int getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(int candidateId) {
        this.candidateId = candidateId;
    }

    public int getSkillId() {
        return skillId;
    }

    public void setSkillId(int skillId) {
        this.skillId = skillId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CandidateSkill that = (CandidateSkill) o;
        return candidateId == that.candidateId && skillId == that.skillId && rating == that.rating && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, candidateId, skillId, rating);
    }
}
