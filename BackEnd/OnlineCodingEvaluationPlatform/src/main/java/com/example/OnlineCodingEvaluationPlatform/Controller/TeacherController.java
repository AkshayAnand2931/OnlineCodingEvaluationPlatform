package com.example.OnlineCodingEvaluationPlatform.Controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.OnlineCodingEvaluationPlatform.Classes.PasswordEncoder;
import com.example.OnlineCodingEvaluationPlatform.Classes.Teacher;
import com.example.OnlineCodingEvaluationPlatform.Service.TeacherService;



@Controller
public class TeacherController {

    private final TeacherService teacherservice;

    
    @Autowired
    public TeacherController(TeacherService teacherservice) {
        this.teacherservice = teacherservice;
    }

    @GetMapping("/teacherlogin")
    public String teacher_login(){
        //List<Teacher> teachers =  teacherservice.getTeachers();
        return "teacherlogin";
    }

    @PostMapping("/teacherlogin")
    public String loginTeacher(@RequestParam("username") String username,
                               @RequestParam("password") String password,
                               RedirectAttributes redirectAttributes) {
        Teacher teacher = teacherservice.findByUsername(username);
        if (teacher != null && PasswordEncoder.checkPassword(password, teacher.getPassword())) {
            // Password matches, login successful
            return "redirect:/Dashboard"; // Currently a placeholder
        } else {
            // Password does not match, login failed
            redirectAttributes.addFlashAttribute("error", "Invalid username or password");
            return "redirect:/teacherlogin"; // Redirect back to login page with error message
        }
    }

    @GetMapping("/teachersignup")
    public String teacher_signup(){
        //List<Teacher> teachers =  teacherservice.getTeachers();
        return "teachersignup";
    }
    
    @PostMapping("/teachersignup")
    public String signupTeacher(@RequestParam("username") String username,
                                @RequestParam("password") String password,
                                @RequestParam("email") String email) {
        Teacher teacher = new Teacher();
        teacher.setUsername(username);
        teacher.setPassword(PasswordEncoder.hashPassword(password));
        teacher.setEmail_id(email);
        teacher.setCompetitions_hosted(new ArrayList<>()); // Empty list initially


        System.out.println(teacher);   //remove this
        teacherservice.addTeacher(teacher);

        return "redirect:/signupSuccess"; // Redirect to signup success page
    }

    @GetMapping("/signupSuccess")
    public String signupSuccess(){
        //List<Teacher> teachers =  teacherservice.getTeachers();
        return "signupSuccess";
    }


    @GetMapping("/Dashboard")
    public String Dashboard(Model model){
        
        model.addAttribute("userRole", new String("teacher"));
        return "Dashboard";
    }

    @PostMapping("/Dashboard")
    public String choose_option(@RequestParam("option") String option) {

        return "redirect:/" + option;
    }
    
}
