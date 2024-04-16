package com.example.OnlineCodingEvaluationPlatform.Classes;

import jakarta.persistence.Embeddable;

@Embeddable
public class CompilationResult {

    private String token;
    private String status_id;
    private String stdout;
    private String stdin;
    private String stderr;
    private String expected_output;
    private double time;
    private String source_code;
    private String compile_output;

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

    public String getStdout() {
        return stdout;
    }

    public void setStdout(String stdout) {
        this.stdout = stdout;
    }

    public String getStderr() {
        return stderr;
    }

    public void setStderr(String stderr) {
        this.stderr = stderr;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public String getSource_code() {
        return source_code;
    }

    public void setSource_code(String source_code) {
        this.source_code = source_code;
    }

    public String getStdin() {
        return stdin;
    }

    public void setStdin(String stdin) {
        this.stdin = stdin;
    }

    public String getExpected_output() {
        return expected_output;
    }

    public void setExpected_output(String expected_output) {
        this.expected_output = expected_output;
    }

    public String getCompile_output() {
        return compile_output;
    }

    public void setCompile_output(String compile_output) {
        this.compile_output = compile_output;
    }
}
