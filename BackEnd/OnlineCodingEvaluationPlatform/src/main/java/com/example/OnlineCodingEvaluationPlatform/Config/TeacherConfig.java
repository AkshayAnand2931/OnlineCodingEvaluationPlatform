package com.example.OnlineCodingEvaluationPlatform.Config;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import com.example.OnlineCodingEvaluationPlatform.Classes.Teacher;
import com.example.OnlineCodingEvaluationPlatform.Repository.TeacherRepository;


@Configuration
@Order(1)
public class TeacherConfig {

    @Bean
    CommandLineRunner commandLineRunner2(TeacherRepository repository){
        return args -> {
            Teacher r1 = new Teacher(1L,
            "user1",
            "pass1",
            "email1",
            List.of(1L, 2L));

            Teacher r2 = new Teacher(2L,
            "user2",
            "pass2",
            "email2",
            List.of(3L, 4L));

            repository.saveAll(List.of(r1, r2));
            // repository.save(r2);
        };
    }
}
