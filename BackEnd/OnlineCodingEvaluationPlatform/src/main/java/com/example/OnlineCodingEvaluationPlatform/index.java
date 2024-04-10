package com.example.OnlineCodingEvaluationPlatform;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class index {

    @GetMapping("/index")
    public String get_index() {
        return "index";
    }

    @PostMapping("/index")
    public String submitRole(@RequestParam("role") String role) {
        // Process the selected role
        System.out.println("Selected role: " + role);
        
        return "redirect:/" + role;
    }
    
}
