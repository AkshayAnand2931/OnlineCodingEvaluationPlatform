package com.example.OnlineCodingEvaluationPlatform.Classes;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Submission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String language;
    private String sourceCode;
    @ElementCollection
    private List<TestCase> testCases;
    @ElementCollection
    private List<CompilationResult> results;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getSourceCode() {
        return sourceCode;
    }

    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }

    public List<TestCase> getTestCases() {
        return testCases;
    }

    public void setTestCases(List<TestCase> testCases) {
        this.testCases = testCases;
    }

    public List<CompilationResult> getResults() {
        return results;
    }

    public void setResults(List<CompilationResult> results) {
        this.results = results;
    }
}
