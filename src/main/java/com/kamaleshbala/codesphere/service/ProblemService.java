package com.kamaleshbala.codesphere.service;


import com.kamaleshbala.codesphere.enums.ProblemType;
import com.kamaleshbala.codesphere.enums.Testcase;
import com.kamaleshbala.codesphere.model.ProblemModel;
import com.kamaleshbala.codesphere.repository.ProblemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProblemService {

    private final ProblemRepo problemRepo;

    @Autowired
    public ProblemService(ProblemRepo problemRepo) {
        this.problemRepo = problemRepo;
    }

    public ProblemModel add(ProblemModel problem) {
        return problemRepo.save(problem);
    }

    public ProblemModel getProblemWithSampleTestcase(String id){
        return problemRepo.findProblemWithSampleTestcases(id, Testcase.SAMPLE);
    }
}
