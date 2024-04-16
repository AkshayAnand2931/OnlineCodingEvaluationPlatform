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
import com.example.OnlineCodingEvaluationPlatform.Classes.Student;
import com.example.OnlineCodingEvaluationPlatform.Service.StudentService;

@Controller
public class StudentController {

    private final StudentService studentservice;

    @Autowired
    public StudentController(StudentService studentservice) {
        this.studentservice = studentservice;
    }

    @GetMapping("/studentlogin")
    public String student_login(){
        //List<Student> students =  studentservice.getStudents();
        return "studentlogin";
    }

    @PostMapping("/studentlogin")
    public String loginStudent(@RequestParam("username") String username,
                               @RequestParam("password") String password,
                               RedirectAttributes redirectAttributes) {
        Student student = studentservice.findByUsername(username);
        if (student != null && PasswordEncoder.checkPassword(password, student.getPassword())) {
            // Password matches, login successful
            return "redirect:/studentDashboard"; // Redirect to student dashboard
        } else {
            // Password does not match, login failed
            redirectAttributes.addFlashAttribute("error", "Invalid username or password");
            return "redirect:/studentlogin"; // Redirect back to login page with error message
        }
    }

    @GetMapping("/studentsignup")
    public String student_signup(){
        //List<Student> students =  studentservice.getStudents();
        return "studentsignup";
    }

    @PostMapping("/studentsignup")
    public String signupStudent(@RequestParam("username") String username,
                                @RequestParam("password") String password,
                                @RequestParam("email") String email) {
        Student student = new Student();
        student.setUsername(username);
        student.setPassword(PasswordEncoder.hashPassword(password));
        student.setEmail_id(email);
        student.setCompetitions_attended(new ArrayList<>()); // Empty list initially

        System.out.println(student);   //remove this
        studentservice.addStudent(student);

        return "redirect:/stusignupSuccess"; // Redirect to signup success page
    }

    @GetMapping("/stusignupSuccess")
    public String signupSuccess(){
        //List<Student> students =  studentservice.getStudents();
        return "stusignupSuccess";
    }


    @GetMapping("/studentDashboard") // Renamed to studentDashboard
    public String studentDashboard(Model model){
        model.addAttribute("userRole", new String("student"));
        return "Dashboard";
    }

    @PostMapping("/studentDashboard") // Renamed to studentDashboard
    public String choose_option(@RequestParam("option") String option) {
        return "redirect:/" + option;
    }
}

