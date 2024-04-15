package com.example.OnlineCodingEvaluationPlatform.Config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.OnlineCodingEvaluationPlatform.Classes.Challenges;
import com.example.OnlineCodingEvaluationPlatform.Classes.Question;
import com.example.OnlineCodingEvaluationPlatform.Classes.TestCase;
import com.example.OnlineCodingEvaluationPlatform.Repository.ChallengesRepository;

@Configuration
public class ChallengeConfig {

    @Bean
    CommandLineRunner commandLineRunner(ChallengesRepository repository){
        return args -> {
        Challenges challenge = new Challenges();


        challenge.setDifficulty(1);
        String s = String.join("\n"
        , "a,b = [int(i) for i in input().split()]"
        , "print(a+b)"
        );
        challenge.setIdeal_answer(s);


        Question question = new Question();
        question.setTitle("Sample question");
        question.setDescription("Write a program to add two numbers.");
        challenge.setQuestion(question);


        List<TestCase> testCases = new ArrayList<>();
        TestCase testcase1 = new TestCase();
        testcase1.setInput("2 3");
        testcase1.setExpectedOutput("5");
        testCases.add(testcase1);

        TestCase testcase2 = new TestCase();
        testcase2.setInput("10 20");
        testcase2.setExpectedOutput("30");
        testCases.add(testcase2);

        challenge.setTestCases(testCases);


        Challenges challenge2 = new Challenges();


        challenge2.setDifficulty(1);
        String s2 = String.join("\n"
        , "a,b = [int(i) for i in input().split()]"
        , "print(a+b)"
        );
        challenge2.setIdeal_answer(s2);


        Question question2 = new Question();
        question2.setTitle("Sample question");
        question2.setDescription("Write a program to add two numbers.");
        challenge2.setQuestion(question2);


        List<TestCase> testCases2 = new ArrayList<>();
        TestCase testcase12 = new TestCase();
        testcase12.setInput("2 3");
        testcase12.setExpectedOutput("5");
        testCases2.add(testcase12);

        TestCase testcase22 = new TestCase();
        testcase22.setInput("10 20");
        testcase22.setExpectedOutput("30");
        testCases2.add(testcase22);

        challenge2.setTestCases(testCases);

        repository.saveAll(List.of(challenge, challenge2));
        // repository.save(r2);
        };
    }
}
