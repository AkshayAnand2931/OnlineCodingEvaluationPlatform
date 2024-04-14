package com.example.OnlineCodingEvaluationPlatform.Controller;


import com.example.OnlineCodingEvaluationPlatform.Classes.*;
import com.example.OnlineCodingEvaluationPlatform.Repository.SubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Controller
public class CompilerController {

    @Autowired
    private Compiler compiler;

    @Autowired
    private SubmissionRepository submissionRepository;

    @GetMapping("/compiler")
    public String showCompilerForm(Model model) {
        model.addAttribute("compilationRequest", new CompilationRequest());
        Question question = new Question();
        question.setTitle("Sample question");
        question.setDescription("Write a program to add two numbers.");
        model.addAttribute("question",question);

        List<TestCase> testCases = new ArrayList<>();
        TestCase testcase1 = new TestCase();
        testcase1.setInput("2 3");
        testcase1.setExpectedOutput("5");
        testCases.add(testcase1);

        TestCase testcase2 = new TestCase();
        testcase2.setInput("10 20");
        testcase2.setExpectedOutput("30");
        testCases.add(testcase2);

        model.addAttribute("testCases",testCases);
        return "compiler";
    }

    @PostMapping("/compiler/compile")
    public String compileAndSaveSubmission(@ModelAttribute CompilationRequest compilationRequest, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "compiler";
        }

        List<CompletableFuture<CompilationResult>> resultFutures = new ArrayList<>();
        List<String> allTokens = new ArrayList<>(); // Store all tokens for debugging

        for (TestCase testCase : compilationRequest.getTestCases()) {
            CompletableFuture<String> tokenFuture = compiler.compile(compilationRequest.getSourceCode(), compilationRequest.getLanguage(), testCase.getInput(), testCase.getExpectedOutput());
            tokenFuture.thenAccept(token -> {
                System.out.println("Token: " + token);
                allTokens.add(token); // Add token to the list
            });

            CompletableFuture<CompilationResult> resultFuture = tokenFuture.thenCompose(token -> compiler.getSubmission(token));
            resultFutures.add(resultFuture);
        }

        CompletableFuture<Void> allResultsFuture = CompletableFuture.allOf(resultFutures.toArray(new CompletableFuture[0]));
        allResultsFuture.join(); // Wait for all CompletableFuture tasks to complete

        List<CompilationResult> compilationResults = resultFutures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());

        for(CompilationResult result: compilationResults){
            String stdinBase64 = result.getStdin();
            if (stdinBase64 != null) {
                String sanitizedBase64 = stdinBase64.replaceAll("\\s", ""); // Remove all whitespace characters
                byte[] decodedBytes = Base64.getDecoder().decode(sanitizedBase64);
                String stdin = new String(decodedBytes, StandardCharsets.UTF_8);
               result.setStdin(stdin);
            }

            String stdoutBase64 = result.getStdout();
            if (stdoutBase64!= null) {
                String sanitizedBase64 = stdoutBase64.replaceAll("\\s", ""); // Remove all whitespace characters
                byte[] decodedBytes = Base64.getDecoder().decode(sanitizedBase64);
                String stdout = new String(decodedBytes, StandardCharsets.UTF_8);
                result.setStdout(stdout);
            }

            String expectedOutputBase64 = result.getExpected_output();
            if (expectedOutputBase64 != null) {
                String sanitizedBase64 = expectedOutputBase64.replaceAll("\\s", ""); // Remove all whitespace characters
                byte[] decodedBytes = Base64.getDecoder().decode(sanitizedBase64);
                String expected_output = new String(decodedBytes, StandardCharsets.UTF_8);
                result.setExpected_output(expected_output);
            }

            String stderrCodeBase64 = result.getStderr();
            if (stderrCodeBase64 != null) {
                String sanitizedBase64 = stderrCodeBase64.replaceAll("\\s", ""); // Remove all whitespace characters
                byte[] decodedBytes = Base64.getDecoder().decode(sanitizedBase64);
                String stderr = new String(decodedBytes, StandardCharsets.UTF_8);
                result.setStderr(stderr);
            }
        }
        model.addAttribute("results", compilationResults);

        // Print all tokens
        System.out.println("All Tokens: " + allTokens);

        return "results";
    }

    @GetMapping("/compiler/submission/{token}")
    public String getSubmission(@PathVariable("token") String submissionToken, Model model) {
        System.out.println("Get submission");
        CompletableFuture<CompilationResult> result = compiler.getSubmission(submissionToken);
        CompilationResult compilationResult = result.join();


        String sourceCodeBase64 = compilationResult.getSource_code();
        if (sourceCodeBase64 != null) {
            String sanitizedBase64 = sourceCodeBase64.replaceAll("\\s", ""); // Remove all whitespace characters
            byte[] decodedBytes = Base64.getDecoder().decode(sanitizedBase64);
            String sourceCode = new String(decodedBytes, StandardCharsets.UTF_8);
            compilationResult.setSource_code(sourceCode);
        }

        String stdinBase64 = compilationResult.getStdin();
        if (stdinBase64 != null) {
            String sanitizedBase64 = stdinBase64.replaceAll("\\s", ""); // Remove all whitespace characters
            byte[] decodedBytes = Base64.getDecoder().decode(sanitizedBase64);
            String stdin = new String(decodedBytes, StandardCharsets.UTF_8);
            compilationResult.setStdin(stdin);
        }

        String stdoutBase64 = compilationResult.getStdout();
        if (stdoutBase64!= null) {
            String sanitizedBase64 = stdoutBase64.replaceAll("\\s", ""); // Remove all whitespace characters
            byte[] decodedBytes = Base64.getDecoder().decode(sanitizedBase64);
            String stdout = new String(decodedBytes, StandardCharsets.UTF_8);
            compilationResult.setStdout(stdout);
        }

        String expectedOutputBase64 = compilationResult.getExpected_output();
        if (expectedOutputBase64 != null) {
            String sanitizedBase64 = expectedOutputBase64.replaceAll("\\s", ""); // Remove all whitespace characters
            byte[] decodedBytes = Base64.getDecoder().decode(sanitizedBase64);
            String expected_output = new String(decodedBytes, StandardCharsets.UTF_8);
            compilationResult.setExpected_output(expected_output);
        }

        String stderrCodeBase64 = compilationResult.getStderr();
        if (stderrCodeBase64 != null) {
            String sanitizedBase64 = stderrCodeBase64.replaceAll("\\s", ""); // Remove all whitespace characters
            byte[] decodedBytes = Base64.getDecoder().decode(sanitizedBase64);
            String stderr = new String(decodedBytes, StandardCharsets.UTF_8);
            compilationResult.setStderr(stderr);
        }

        model.addAttribute("result", compilationResult);
        return "submission";
    }
}
