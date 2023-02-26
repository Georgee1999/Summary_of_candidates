package com.example.summaryofcandidates.db.service.impl;

import com.example.summaryofcandidates.Entity.Candidate;
import com.example.summaryofcandidates.db.repository.CandidateRepository;
import com.example.summaryofcandidates.db.service.api.CandidateService;
import com.example.summaryofcandidates.db.service.api.request.UpdateCandidateRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidateServiceImpl implements CandidateService {

    public final CandidateRepository candidateRepository;

    public CandidateServiceImpl(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }


    @Override
    public Integer add(Candidate candidate) {
        return candidateRepository.add(candidate);
    }

    @Override
    public void update(int id, UpdateCandidateRequest request) {
        candidateRepository.update(id,request);
    }

    @Override
    public void delete(int id) {
        candidateRepository.delete(id);
    }

    @Override
    public Candidate get(int id) {
        return candidateRepository.get(id);
    }

    @Override
    public List<Candidate> getCandidates() {
        return candidateRepository.getCandidates();
    }
}
