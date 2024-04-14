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

import java.util.ArrayList;
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

        System.out.println(compilationRequest.getSourceCode());
        System.out.println(compilationRequest.getLanguage());
        System.out.println(compilationRequest.getTestCases());

        List<CompletableFuture<String>> tokenFutures = new ArrayList<>();

        for(TestCase testCase: compilationRequest.getTestCases()){
            CompletableFuture<String> tokenFuture = compiler.compile(compilationRequest.getSourceCode(), compilationRequest.getLanguage(),testCase.getInput());
            System.out.println(tokenFuture);
            tokenFutures.add(tokenFuture);
        }

        CompletableFuture<Void> allTokensFuture = CompletableFuture.allOf(tokenFutures.toArray(new CompletableFuture[0]));
        List<String> submissionTokens = allTokensFuture.thenApply(v ->
                tokenFutures.stream()
                        .map(CompletableFuture::join)
                        .collect(Collectors.toList())
        ).join();

        for(String token: submissionTokens){
            System.out.println(token);
        }


//        Submission submission = new Submission();
//        submission.setLanguage(compilationRequest.getLanguage());
//        submission.setSourceCode(compilationRequest.getSourceCode());
//        submission.setTestCases(compilationRequest.getTestCases());
//        submission.setResults(results);
//        submissionRepository.save(submission);
//
//        model.addAttribute("results", results);
        return "results";
    }

    @GetMapping("/compiler/submission/{token}")
    public String getSubmission(@PathVariable("token") String submissionToken, Model model) {
        CompletableFuture<CompilationResult> result = compiler.getSubmission(submissionToken);
        CompilationResult compilationResult = result.join();
        model.addAttribute("result", compilationResult);
        return "submission";
    }
}
