package com.example.OnlineCodingEvaluationPlatform.Classes;

import java.util.List;

import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Challenges{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long challenge_id;
    private int difficulty; // keep this 1 to 5
    private String ideal_answer;

    @Convert(converter = QuestionConverter.class)
    private Question question;

    @Convert(converter = TestCaseConverter.class)
    private List<TestCase> testCases;

    public Question getQuestion() {
        return question;
    }
    public void setQuestion(Question question) {
        this.question = question;
    }
    public void setChallenge_id(Long challenge_id){
        this.challenge_id = challenge_id;
    }
    public Long getChallenge_id(){
        return this.challenge_id;
    }
    public void setDifficulty(int difficulty){
        this.difficulty = difficulty;
    }
    public int getDifficulty(){
        return this.difficulty;
    }
    public void setIdeal_answer(String ideal_answer){
        this.ideal_answer = ideal_answer;
    }
    public String getIdeal_answer(){
        return this.ideal_answer;
    }
    public List<TestCase> getTestCases() {
        return testCases;
    }
    public void setTestCases(List<TestCase> testCases) {
        this.testCases = testCases;
    }
    @Override
    public String toString() {
        return "Challenges [challenge_id=" + challenge_id + ", difficulty=" + difficulty + ", ideal_answer="
                + ideal_answer + ", question=" + question + ", testCases=" + testCases + "]";
    }

}