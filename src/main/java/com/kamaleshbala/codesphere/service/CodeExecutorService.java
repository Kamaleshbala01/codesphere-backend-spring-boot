package com.kamaleshbala.codesphere.service;

import com.kamaleshbala.codesphere.enums.ExecutionStatus;
import com.kamaleshbala.codesphere.model.*;
import com.kamaleshbala.codesphere.repository.SubmissionRepo;
import com.kamaleshbala.codesphere.repository.TestcaseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class CodeExecutorService {

    private final TestcaseRepo testcaseRepo;
    private final SubmissionRepo submissionRepo;

    @Autowired
    public CodeExecutorService(TestcaseRepo testcaseRepo
            ,SubmissionRepo submissionRepo) {
        this.testcaseRepo = testcaseRepo;
        this.submissionRepo = submissionRepo;
    }


    public SubmissionModel execute(SubmissionModel submission) throws IOException {

        List<TestcaseModel> testcases = testcaseRepo.findByProblem_id(submission.getProblem().getId());
        Path codeFile = Paths.get(submission.getLanguage().getFileName());
        Files.write(codeFile, submission.getCode().getBytes());

        UserPrinciple user = (UserPrinciple) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        submission.setUser(user.getUser());

        for(TestcaseModel testcase : testcases) {
            Path inputFile = Paths.get("input.txt");
            Files.write(inputFile, testcase.getTestcase().getBytes());

            ProcessBuilder pb = new ProcessBuilder(
                    "docker", "run", "--rm",
                    "-v", codeFile.toAbsolutePath().toString() + ":/app/" + submission.getLanguage().getFileName(),
                    "-v", inputFile.toAbsolutePath().toString() + ":/app/input.txt",
                    submission.getLanguage().getName()
            );

            Process process = pb.start();
            String res =  new String(process.getInputStream().readAllBytes());
            ExecutedTestcaseModel executedTestcase = getExecutedTestcaseModel(testcase, res,submission);
            if(executedTestcase.getStatus() == ExecutionStatus.PASS) {
                submission.setPassedTestcases(submission.getPassedTestcases() + 1);
                submission.setTotalScore(submission.getTotalScore() + testcase.getScore());
            }
            submission.getExecutedTestcases().add(executedTestcase);
            System.out.println("res : " + res);
        }
        return submissionRepo.save(submission);
    }

    public CodeModel runSampleTestCase(CodeModel code) throws IOException {
        Path codeFile = Paths.get(code.getLanguage().getFileName());
        Files.write(codeFile, code.getCode().getBytes());

        for(String testcase : code.getInputs()) {
            Path inputFile = Paths.get("input.txt");
            Files.write(inputFile, testcase.getBytes());

            ProcessBuilder pb = new ProcessBuilder(
                    "docker", "run", "--rm",
                    "-v", codeFile.toAbsolutePath().toString() + ":/app/" + code.getLanguage().getFileName(),
                    "-v", inputFile.toAbsolutePath().toString() + ":/app/input.txt",
                    code.getLanguage().getName()
            );
            Process process = pb.start();
            String res =  new String(process.getInputStream().readAllBytes());
            code.getOutput().add(res.substring(2).trim());
        }
        return code;
    }

    private static ExecutedTestcaseModel getExecutedTestcaseModel(TestcaseModel testcase, String res,SubmissionModel submission) {
        ExecutedTestcaseModel executedTestcase = new ExecutedTestcaseModel();
        executedTestcase.setSubmission(submission);
        executedTestcase.setTestcase(testcase);
        executedTestcase.setStatus(ExecutionStatus.FAIL);

        if(!res.isEmpty()){
            String[] results = testcase.getResult().split(",");
            executedTestcase.setUserOutput(res.substring(2).trim());

            if(res.charAt(0) == '0'){
                executedTestcase.setStatus(ExecutionStatus.FAIL);
                for(String result : results) {
                    if (res.substring(2).trim().equalsIgnoreCase(result.trim())) {
                        executedTestcase.setStatus(ExecutionStatus.PASS);
                        break;
                    }
                }
            }else{
                if(res.charAt(0) == '1'){
                    executedTestcase.setStatus(ExecutionStatus.COMPILE_TIME_ERROR);
                } else if (res.charAt(0) == '2') {
                    executedTestcase.setStatus(ExecutionStatus.TIME_LIMIT_EXCEEDED);
                }else executedTestcase.setStatus(ExecutionStatus.RUNTIME_ERROR);
            }
        }

        return executedTestcase;
    }
}

