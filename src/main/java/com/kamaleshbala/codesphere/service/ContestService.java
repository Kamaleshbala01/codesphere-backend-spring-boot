package com.kamaleshbala.codesphere.service;

import com.kamaleshbala.codesphere.enums.Testcase;
import com.kamaleshbala.codesphere.model.ContestModel;
import com.kamaleshbala.codesphere.model.ProblemModel;
import com.kamaleshbala.codesphere.repository.ContestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContestService {
    private final ContestRepo contestRepo;

    @Autowired
    public ContestService(ContestRepo contestRepo) {
        this.contestRepo = contestRepo;
    }

    public ContestModel getContest(String id){
        ContestModel contest =  contestRepo.findById(id).orElseThrow();

        for(ProblemModel problem : contest.getProblems()){
            problem.setTestcases(
                    problem.getTestcases().stream()
                            .filter(p -> p.getType() == Testcase.SAMPLE)
                            .toList()
            );
        }
        return contest;
    }

    public ContestModel createContest(ContestModel contest){
        return contestRepo.save(contest);
    }

    public ContestModel addProblem(String id,ProblemModel problem){
        ContestModel contest = contestRepo.findById(id).orElseThrow();
        contest.getProblems().add(problem);
        return contestRepo.save(contest);
    }
}
