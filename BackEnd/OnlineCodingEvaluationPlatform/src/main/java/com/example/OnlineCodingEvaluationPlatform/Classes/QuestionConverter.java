package com.example.OnlineCodingEvaluationPlatform.Classes;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class QuestionConverter implements AttributeConverter<Question, String>{
    private static final String SEPARATOR = ", ";

    @Override
    public String convertToDatabaseColumn(Question question) {
        if (question == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        if (question.getTitle() != null && !question.getTitle()
            .isEmpty()) {
            sb.append(question.getTitle());
            sb.append(SEPARATOR);
        }

        if (question.getDescription() != null && !question.getDescription().isEmpty()) {
            sb.append(question.getDescription());
        }

        return sb.toString();
    }

    @Override
    public Question convertToEntityAttribute(String dbQuestion) {
        if (dbQuestion == null || dbQuestion.isEmpty()) {
            return null;
        }

        String[] pieces = dbQuestion.split(SEPARATOR);

        if (pieces == null || pieces.length == 0) {
            return null;
        }

        Question question = new Question();
        String firstPiece = !pieces[0].isEmpty() ? pieces[0] : null;
        if (dbQuestion.contains(SEPARATOR)) {
            question.setTitle(firstPiece);
            if (pieces.length >= 2 && pieces[1] != null && !pieces[1].isEmpty()) {
                question.setDescription(firstPiece);
            }
        } else {
            question.setTitle(firstPiece);
        }

        return question;
    }
}
