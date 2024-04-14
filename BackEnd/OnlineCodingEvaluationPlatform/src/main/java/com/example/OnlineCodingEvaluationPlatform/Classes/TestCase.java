package com.example.OnlineCodingEvaluationPlatform.Classes;

import jakarta.persistence.Embeddable;

@Embeddable
public class TestCase {
    private String input;
    private String expectedOutput;

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getExpectedOutput() {
        return expectedOutput;
    }

    public void setExpectedOutput(String expectedOutput) {
        this.expectedOutput = expectedOutput;
    }
}
