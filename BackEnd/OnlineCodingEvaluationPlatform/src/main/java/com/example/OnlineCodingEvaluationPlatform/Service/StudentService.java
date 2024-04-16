package com.example.OnlineCodingEvaluationPlatform.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.OnlineCodingEvaluationPlatform.Classes.Student;
import com.example.OnlineCodingEvaluationPlatform.Repository.StudentRepository;

@Service
public class StudentService {

	private final StudentRepository studentRepository;

	@Autowired
    public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}


	public List<Student> getStudent(){
		return studentRepository.findAll();
	}

	public void addStudent(Student student){
		studentRepository.save(student);
	}


	public Student findByUsername(String username) {
		Student student = studentRepository.findByUsername(username);
		return student;
	}
}
