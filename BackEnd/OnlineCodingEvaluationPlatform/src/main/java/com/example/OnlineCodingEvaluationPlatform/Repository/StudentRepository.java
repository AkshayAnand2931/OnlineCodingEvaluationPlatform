package com.example.OnlineCodingEvaluationPlatform.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.OnlineCodingEvaluationPlatform.Classes.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long>{
    Student findByUsername(String username);
}
