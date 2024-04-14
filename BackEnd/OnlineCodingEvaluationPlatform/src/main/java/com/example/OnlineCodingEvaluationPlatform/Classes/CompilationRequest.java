package com.example.OnlineCodingEvaluationPlatform.Classes;

import java.util.List;

public class CompilationRequest {
    private String language;
    private String sourceCode;
    private List<TestCase> testCases;

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
}