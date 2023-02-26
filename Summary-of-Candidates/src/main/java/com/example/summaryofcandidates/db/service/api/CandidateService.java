package com.example.summaryofcandidates.db.service.api;

import com.example.summaryofcandidates.Entity.Candidate;
import com.example.summaryofcandidates.db.service.api.request.UpdateCandidateRequest;
import org.springframework.lang.Nullable;

import java.util.List;

public interface CandidateService {

    Integer add (Candidate candidate);

    void update(int id, UpdateCandidateRequest request);

    void delete(int id);

    @Nullable
    Candidate get (int id);

    List<Candidate> getCandidates();
}
