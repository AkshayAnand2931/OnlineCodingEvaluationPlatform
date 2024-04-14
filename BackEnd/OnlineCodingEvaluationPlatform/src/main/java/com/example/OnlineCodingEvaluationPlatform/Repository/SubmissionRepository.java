package com.example.OnlineCodingEvaluationPlatform.Repository;

import com.example.OnlineCodingEvaluationPlatform.Classes.Submission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubmissionRepository extends JpaRepository<Submission, Long> {
}
