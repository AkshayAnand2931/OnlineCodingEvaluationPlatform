package com.example.OnlineCodingEvaluationPlatform.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.OnlineCodingEvaluationPlatform.Classes.Teacher;
import com.example.OnlineCodingEvaluationPlatform.Service.TeacherService;


@RestController
@RequestMapping("/teacher")
public class TeacherController {

    private final TeacherService teacherservice;

    
    @Autowired
    public TeacherController(TeacherService teacherservice) {
        this.teacherservice = teacherservice;
    }



    @GetMapping
    public List<Teacher> getTeachers(){
        List<Teacher> teachers =  teacherservice.getTeachers();
        return teachers;
    }
}
