package com.example.OnlineCodingEvaluationPlatform.Classes;

import java.util.List;

import jakarta.persistence.Entity;



@Entity
public class Teacher extends User{

    private List<Long> competitions_hosted;

    public Teacher() {
        super();
    }

    public Teacher(Long id, String username, String password, String email_id, List<Long> competitions_hosted) {
        super(id, username, password, email_id);
        this.competitions_hosted = competitions_hosted;
    }

    public List<Long> getCompetitions_hosted() {
        return competitions_hosted;
    }

    public void setCompetitions_hosted(List<Long> competitions_hosted) {
        this.competitions_hosted = competitions_hosted;
    }

    @Override
    public String toString() {
        return "Teacher [id=" + getId() + ", username=" + getUsername() + ", password=" + getPassword() + ", email_id=" + getEmail_id() + ", competitions_hosted=" + competitions_hosted + "]";
    }
    
}
