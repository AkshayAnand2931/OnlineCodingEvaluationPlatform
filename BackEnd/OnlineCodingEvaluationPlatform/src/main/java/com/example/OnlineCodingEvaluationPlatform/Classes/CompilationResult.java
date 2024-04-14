package com.example.OnlineCodingEvaluationPlatform.Classes;

import jakarta.persistence.Embeddable;

@Embeddable
public class CompilationResult {

    private String token;
    private String status_id;
    private String standard_output;
    private String standard_error;
    private double time;
    private int memory;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getStatus_id() {
        return status_id;
    }

    public void setStatus_id(String status_id) {
        this.status_id = status_id;
    }

    public String getStandard_output() {
        return standard_output;
    }

    public void setStandard_output(String standard_output) {
        this.standard_output = standard_output;
    }

    public String getStandard_error() {
        return standard_error;
    }

    public void setStandard_error(String standard_error) {
        this.standard_error = standard_error;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public int getMemory() {
        return memory;
    }

    public void setMemory(int memory) {
        this.memory = memory;
    }
}
