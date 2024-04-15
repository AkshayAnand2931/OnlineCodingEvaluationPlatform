package com.example.OnlineCodingEvaluationPlatform.Classes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TestCaseConverter implements AttributeConverter<TestCase, String> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(TestCase testCase) {
        try {
            return objectMapper.writeValueAsString(testCase);
        } catch (JsonProcessingException e) {
            // Handle exception
            return null;
        }
    }

    @Override
    public TestCase convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, TestCase.class);
        } catch (JsonProcessingException e) {
            // Handle exception
            return null;
        }
    }
}
