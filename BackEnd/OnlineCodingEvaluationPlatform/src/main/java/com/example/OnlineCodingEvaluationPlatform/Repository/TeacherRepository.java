package com.example.OnlineCodingEvaluationPlatform.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.OnlineCodingEvaluationPlatform.Classes.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher,Long>{
    Teacher findByUsername(String username);
}
