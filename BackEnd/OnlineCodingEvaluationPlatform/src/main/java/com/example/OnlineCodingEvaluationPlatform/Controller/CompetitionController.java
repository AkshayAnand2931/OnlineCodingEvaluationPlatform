package com.example.OnlineCodingEvaluationPlatform.Controller;

import java.util.Optional;
import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.OnlineCodingEvaluationPlatform.Classes.Competition;
import com.example.OnlineCodingEvaluationPlatform.Repository.CompetitionRepository;

@Controller
public class CompetitionController{
    @Autowired
    private CompetitionRepository competitionRepository;

    @GetMapping("/viewCompetitions")
    public String getComp(Model model){
        model.addAttribute("competitions", competitionRepository.findAll());
        return "viewCompetitions";
    }

    @GetMapping("/viewCompetitions/{id}")
    public String viewComp(@PathVariable("token") String submissionToken, @ModelAttribute Competition competition, Model model){
        model.addAttribute("competition", competition);
        return "competition";
    }

    @PostMapping("/submitCompetition")
    public String postComp(@RequestParam("compId") Long id, RedirectAttributes redirectAttributes, Model model){
        
        Optional<Competition> optionalcompetition = competitionRepository.findById(id);

        Competition competition;
        if(optionalcompetition.isPresent()){
            competition = optionalcompetition.get();
        } else{
            redirectAttributes.addFlashAttribute("error", "Cannot find competition");
            return "redirect:/viewCompetitions";
        }
        model.addAttribute("competition", competition);
        return "redirect:/viewCompetitions/"+id;
    }
    
    @PostMapping("/createCompetition")
    public String createCompMethod(@RequestParam("title") String title, @RequestParam("challenges") String challenges, @RequestParam("time_limit") int time_limit, @RequestParam("difficulty") int difficulty, RedirectAttributes redirectAttributes, Model model){
        List<Long> l = new ArrayList<Long>();
        String[] k = challenges.split(",");
        List<Long> challenge_id = new ArrayList<Long>();
        for(String i:k){
            challenge_id.add(Long.parseLong(i));
        }
        Competition c = new Competition(title, challenge_id, time_limit, difficulty, 5L, l);
        competitionRepository.save(c);
        return "redirect:/viewCompetitions";
    }

    @GetMapping("/createCompetition")
    public String createCompPage(RedirectAttributes redirectAttributes, Model model){
        return "createCompetition";
    }
}