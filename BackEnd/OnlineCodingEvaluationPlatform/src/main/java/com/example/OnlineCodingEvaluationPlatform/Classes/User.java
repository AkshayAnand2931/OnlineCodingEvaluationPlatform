package com.example.OnlineCodingEvaluationPlatform.Classes;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.SequenceGenerator;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class User {

    @SequenceGenerator(
        name = "user_sequence",
        sequenceName = "user_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "user_sequence"
    )

    @jakarta.persistence.Id
    private Long id;
    private String username;
    private String password;
    private String email_id;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail_id() {
        return email_id;
    }
    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

    
    public User() {
    }
    public User(Long id, String username, String password, String email_id) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email_id = email_id;
    }

    

    
}
