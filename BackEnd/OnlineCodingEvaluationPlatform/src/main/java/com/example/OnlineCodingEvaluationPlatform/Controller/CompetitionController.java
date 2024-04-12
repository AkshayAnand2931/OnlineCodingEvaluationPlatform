package com.example.OnlineCodingEvaluationPlatform.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.OnlineCodingEvaluationPlatform.Repository.CompetitionRepository;
import com.example.OnlineCodingEvaluationPlatform.Classes.Competition;

@Controller
@RequestMapping("/comp")
public class CompetitionController{
    @Autowired
    private CompetitionRepository competitionRepository;

    @GetMapping("/get")
    public Competition getComp(@RequestParam("id") Long id){
        Optional<Competition> c= competitionRepository.findById(id);
        Competition c1 = c.get();
        return c1;
    }

    @PostMapping("/post")
    public @ResponseBody String postComp(@RequestParam("challenges") List<Long> challenges, 
    @RequestParam("time_limit") int time_limit, @RequestParam("students") List<Long> students,
    @RequestParam("difficulty") int difficulty, @RequestParam("created_by") Long created_by
    ){
        List<Long> l = new ArrayList<Long>();
        Competition c = new Competition(challenges, time_limit, students, difficulty, created_by, l);
        return "Created new competition";
    }
}