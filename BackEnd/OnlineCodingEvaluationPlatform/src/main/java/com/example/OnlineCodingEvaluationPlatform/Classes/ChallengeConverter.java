package com.example.OnlineCodingEvaluationPlatform.Classes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ChallengeConverter implements AttributeConverter<Challenges, String> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Challenges challenge) {
        try {
            return objectMapper.writeValueAsString(challenge);
        } catch (JsonProcessingException e) {
            // Handle exception
            return null;
        }
    }

    @Override
    public Challenges convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, Challenges.class);
        } catch (JsonProcessingException e) {
            // Handle exception
            return null;
        }
    }
}
