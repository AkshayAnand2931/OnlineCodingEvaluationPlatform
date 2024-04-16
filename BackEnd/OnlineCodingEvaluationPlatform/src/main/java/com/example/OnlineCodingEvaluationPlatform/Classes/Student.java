package com.example.OnlineCodingEvaluationPlatform.Classes;

import java.util.List;

import jakarta.persistence.Entity;

@Entity
public class Student extends User {

    private List<Long> competitions_attended;

    public Student() {
        super();
    }

    public Student(Long id, String username, String password, String email_id, List<Long> competitions_attended) {
        super(id, username, password, email_id);
        this.competitions_attended = competitions_attended;
    }

    public List<Long> getCompetitions_attended() {
        return competitions_attended;
    }

    public void setCompetitions_attended(List<Long> competitions_attended) {
        this.competitions_attended = competitions_attended;
    }

    @Override
    public String toString() {
        return "Student [id=" + getId() + ", username=" + getUsername() + ", password=" + getPassword() + ", email_id=" + getEmail_id() + ", competitions_attended=" + competitions_attended + "]";
    }
}
