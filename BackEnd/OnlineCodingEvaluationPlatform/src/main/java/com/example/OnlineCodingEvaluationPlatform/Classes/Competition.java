package com.example.OnlineCodingEvaluationPlatform.Classes;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Competition{

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String name;
    private List<Long> challenges;


    private int time_limit;
    private List<Long> students;
    private int difficulty; // keep it from 1 to 5 or avg of all challenges difficulty rating
    private Long created_by;
    private List<Long> leaderboard; // save it as a always sorted list of ids???that is the best I could think of lol



    public Competition(){

    }
    public Competition(String name, List<Long> challenges, int time_limit, int difficulty, Long created_by, List<Long> leaderboard){
        //this.id = id;
        this.name=name;
        this.challenges = challenges;
        this.time_limit = time_limit;
        //this.students = students;
        this.difficulty = difficulty;
        this.created_by = created_by;
        this.leaderboard = leaderboard;
    }
    

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id){
        this.id = id;
    }
    public Long getId(){
        return this.id;
    }
    
    public void setTime_limit(int time_limit){
        this.time_limit = time_limit;
    }
    public int getTime_limit(){
        return this.time_limit;
    }
    public void setStudents(List<Long> students){
        this.students = students;
    }
    public List<Long> getStudents(){
        return this.students;
    }
    public void setDifficulty(int difficulty){
        this.difficulty = difficulty;
    }
    public int getDifficulty(){
        return this.difficulty;
    }
    public void setCreated_by(Long created_by){
        this.created_by = created_by;
    }
    public Long getCreated_by(){
        return this.created_by;
    }
    public void setLeaderboard(List<Long> leaderboard){
        this.leaderboard = leaderboard;
    }
    public List<Long> getLeaderboard(){
        return this.leaderboard;
    }
    public List<Long> getChallenges() {
        return challenges;
    }
    public void setChallenges(List<Long> challenges) {
        this.challenges = challenges;
    }

    // public void setChallengebyId(Long id, ChallengesRepository challengeRepository){
    //     Optional<Challenges> optionalchallenge = challengeRepository.findById(id);

    //     Challenges challenge = null;
    //     if(optionalchallenge.isPresent()){
    //         challenge = optionalchallenge.get();
    //         challenges.add(challenge);
    //     }
        
    // }
}