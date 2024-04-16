package com.example.OnlineCodingEvaluationPlatform.Config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import com.example.OnlineCodingEvaluationPlatform.Classes.Competition;
import com.example.OnlineCodingEvaluationPlatform.Repository.ChallengesRepository;
import com.example.OnlineCodingEvaluationPlatform.Repository.CompetitionRepository;

@Configuration
@Order(3)
public class CompetitionConfig {

    @Bean
    CommandLineRunner commandLineRunner3(CompetitionRepository repository, ChallengesRepository challengeRepository){
        return args -> {
        Competition competition = new Competition();
        
        competition.setId(1L);
        competition.setName("Beginners Competition");
        competition.setTime_limit(90);

        List<Long> challenges = new ArrayList<>();
        challenges.add(1L);
        challenges.add(2L);
        competition.setChallenges(challenges);

        competition.setDifficulty(2);

        repository.save(competition);
        // repository.save(r2);
        };
    }
}
