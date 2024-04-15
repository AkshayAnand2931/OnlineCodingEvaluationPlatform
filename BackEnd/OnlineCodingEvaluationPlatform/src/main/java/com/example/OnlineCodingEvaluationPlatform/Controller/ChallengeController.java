package com.example.OnlineCodingEvaluationPlatform.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.OnlineCodingEvaluationPlatform.Repository.ChallengesRepository;


@Controller
public class ChallengeController {

    @Autowired
    private ChallengesRepository challengesRepository;


    @GetMapping("/viewChallenges")
    public String viewChallenges(Model model) {
        model.addAttribute("challenges", challengesRepository.findAll());
        return "viewChallenges";
    }

    @PostMapping("/submitChallenge")
    public String submitChallenge(@RequestParam("challengeId") Long challengeId) {
        // Handle the submitted challenge ID here
        System.out.println("Challenge ID: " + challengeId);
        return "redirect:/compiler/"+challengeId; // Redirect back to the challenges page
    }
    
}
