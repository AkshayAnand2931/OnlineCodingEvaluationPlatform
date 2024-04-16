package com.example.OnlineCodingEvaluationPlatform.Controller;


import com.example.OnlineCodingEvaluationPlatform.Classes.*;
import com.example.OnlineCodingEvaluationPlatform.Repository.ChallengesRepository;
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
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Controller
public class CompilerController {

    @Autowired
    private Compiler compiler;

    @Autowired
    private SubmissionRepository submissionRepository;

    @Autowired
    private ChallengesRepository challengesRepository;

    @GetMapping("/compiler/{challengeId}")
    public String showCompilerForm(@PathVariable Long challengeId, Model model) {
        model.addAttribute("code", new Code());

        //get challenge from sql
        Optional<Challenges> optionalChallenges = challengesRepository.findById(challengeId);

        Challenges challenge = null;
        if (optionalChallenges.isPresent()) {
            challenge = optionalChallenges.get();
        }

        model.addAttribute("challenge", challenge);
        return "compiler";
    }

    private String convertfrom64String(String string64){
        if (string64 != null) {
            String sanitizedBase64 = string64.replaceAll("\\s", ""); // Remove all whitespace characters
            byte[] decodedBytes = Base64.getDecoder().decode(sanitizedBase64);
            String string = new String(decodedBytes, StandardCharsets.UTF_8);
            return string;
        }
        return string64;
    }

    @PostMapping("/compiler/compile")
    public String compileAndSaveSubmission(@ModelAttribute Challenges challenge, @ModelAttribute Code code, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "compiler";
        }

        List<CompletableFuture<CompilationResult>> resultFutures = new ArrayList<>();
        List<String> allTokens = new ArrayList<>(); // Store all tokens for debugging
        System.out.println(challenge);
        System.out.println(code);

        for (TestCase testCase : challenge.getTestCases()) {
            CompletableFuture<String> tokenFuture = compiler.compile(code.getSourceCode(), code.getLanguage(), testCase.getInput(), testCase.getExpectedOutput());
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

        boolean final_result = true;

        for(CompilationResult result: compilationResults){

            String stdinBase64 = result.getStdin();
            String stdoutBase64 = result.getStdout();
            String expectedOutputBase64 = result.getExpected_output();
            String stderrCodeBase64 = result.getStderr();
            String compile_outputBase64 = result.getCompile_output();

            result.setStdin(convertfrom64String(stdinBase64));
            result.setStdout(convertfrom64String(stdoutBase64));
            result.setExpected_output(convertfrom64String(expectedOutputBase64));
            if(stderrCodeBase64 == null){
                result.setStderr(convertfrom64String(compile_outputBase64));
            }
            else{
                result.setStderr(convertfrom64String(stderrCodeBase64));
            }

            if(stdoutBase64 == null){
                final_result = false;
            }
            else if (!convertfrom64String(stdoutBase64).trim().equals(convertfrom64String(expectedOutputBase64).trim())){
                System.out.println(convertfrom64String(stdoutBase64) + ": "+ convertfrom64String(expectedOutputBase64));
                final_result = false;
            }

        }
        model.addAttribute("results", compilationResults);
        model.addAttribute("success", final_result);

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
        String stdinBase64 = compilationResult.getStdin();
        String stdoutBase64 = compilationResult.getStdout();
        String expectedOutputBase64 = compilationResult.getExpected_output();
        String stderrCodeBase64 = compilationResult.getStderr();

        compilationResult.setSource_code(convertfrom64String(sourceCodeBase64));
        compilationResult.setStdin(convertfrom64String(stdinBase64));
        compilationResult.setStdout(convertfrom64String(stdoutBase64));
        compilationResult.setExpected_output(convertfrom64String(expectedOutputBase64));
        compilationResult.setStderr(convertfrom64String(stderrCodeBase64));

        

        model.addAttribute("result", compilationResult);
        return "submission";
    }
}
