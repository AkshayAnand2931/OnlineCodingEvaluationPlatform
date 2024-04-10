package com.example.OnlineCodingEvaluationPlatform.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.OnlineCodingEvaluationPlatform.Classes.Teacher;
import com.example.OnlineCodingEvaluationPlatform.Repository.TeacherRepository;

@Service
public class TeacherService {

	private final TeacherRepository teacherRepository;

	@Autowired
    public TeacherService(TeacherRepository teacherRepository) {
		this.teacherRepository = teacherRepository;
	}


	public List<Teacher> getTeachers(){
		return teacherRepository.findAll();
	}

	public void addTeacher(Teacher teacher){
		teacherRepository.save(teacher);
	}


	public Teacher findByUsername(String username) {
		Teacher teacher = teacherRepository.findByUsername(username);
		return teacher;
	}
}
