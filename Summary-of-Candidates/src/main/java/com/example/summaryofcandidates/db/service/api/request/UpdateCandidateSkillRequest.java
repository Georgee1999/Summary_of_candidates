package com.example.summaryofcandidates.db.service.api.request;

import org.springframework.lang.NonNull;

import java.util.Objects;

public class UpdateCandidateSkillRequest {

    @NonNull
    private int rating;

    public UpdateCandidateSkillRequest(int rating) {
        this.rating = rating;
    }
    public UpdateCandidateSkillRequest() {}


    public int getRating() {
        return rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdateCandidateSkillRequest that = (UpdateCandidateSkillRequest) o;
        return rating == that.rating;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rating);
    }
}
