package com.example.summaryofcandidates.db.service.api.request;

import org.springframework.lang.NonNull;

import java.util.Objects;

public class UpdateSkillRequest {
    @NonNull
    private String description;


    public UpdateSkillRequest( @NonNull String description) {
        this.description = description;
    }

    @NonNull
    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdateSkillRequest that = (UpdateSkillRequest) o;
        return description.equals(that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description);
    }
}
