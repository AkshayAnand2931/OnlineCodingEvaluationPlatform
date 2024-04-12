package com.example.OnlineCodingEvaluationPlatform.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.OnlineCodingEvaluationPlatform.Classes.Competition;

@Repository
public interface CompetitionRepository extends CrudRepository<Competition, Long> {
    
}
