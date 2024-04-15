package com.example.OnlineCodingEvaluationPlatform.Classes;

public class Code {
    private String language;
    private String sourceCode;

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

    @Override
    public String toString() {
        return "Code [language=" + language + ", sourceCode=" + sourceCode + "]";
    }

}