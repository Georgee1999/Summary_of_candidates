package com.example.summaryofcandidates.Entity;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Objects;

public class Skill {


    @Nullable
    private Integer id;
    @NonNull
    private String technology;
    @NonNull
    private String description;
  


    public Skill(@NonNull String technology, @NonNull String description) {
        this.technology = technology;
        this.description = description;
    }

    public Skill() {}

    @Nullable
    public Integer getId() {
        return id;
    }

    public void setId(@Nullable Integer id) {
        this.id = id;
    }

    @NonNull
    public String getTechnology() {
        return technology;
    }

    public void setTechnology(@NonNull String technology) {
        this.technology = technology;
    }
    @NonNull
    public String getDescription() {
        return description;
    }

    public void setDescription(@NonNull String description) {
        this.description = description;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Skill skill = (Skill) o;
        return Objects.equals(id, skill.id) &&
                technology.equals(skill.technology) &&
                description.equals(skill.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, technology, description);
    }
}
