package com.example.OnlineCodingEvaluationPlatform.Classes;

import java.util.List;
import java.util.Map;

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
    private List<String> tests;

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
    public void setTests(List<String> tests){
        this.tests = tests;
    }
    public List<String> getTests(){
        return this.tests;
    }
}