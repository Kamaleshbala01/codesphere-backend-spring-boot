package com.kamaleshbala.codesphere.service;

import com.kamaleshbala.codesphere.DTO.ContestDTO;
import com.kamaleshbala.codesphere.DTO.ContestSkeletonDTO;
import com.kamaleshbala.codesphere.enums.Testcase;
import com.kamaleshbala.codesphere.mapper.ContestMapper;
import com.kamaleshbala.codesphere.model.ContestModel;
import com.kamaleshbala.codesphere.model.ProblemModel;
import com.kamaleshbala.codesphere.model.UserPrinciple;
import com.kamaleshbala.codesphere.model.ViolationModel;
import com.kamaleshbala.codesphere.repository.ContestRepo;
import com.kamaleshbala.codesphere.repository.ViolationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class ContestService {
    private final ContestRepo contestRepo;
    private final ViolationRepo violationRepo;

    @Autowired
    public ContestService(ContestRepo contestRepo,ViolationRepo violationRepo) {
        this.contestRepo = contestRepo;
        this.violationRepo = violationRepo;
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


    // get all contest include (upcoming,past,and ongoing contest

    public ContestSkeletonDTO getContestSkeleton(){
        ContestSkeletonDTO contestSkeleton = new ContestSkeletonDTO();
        ContestMapper mapper = new ContestMapper();
        List<ContestDTO> upcomingContest = new ArrayList<>();
        List<ContestDTO> ongoingContest = new ArrayList<>();
        List<ContestDTO> pastContest = new ArrayList<>();


        List<ContestModel> allContests = contestRepo.findAll();
        allContests.forEach(contest -> {
            Instant now = Instant.now();
            if(contest.getEndAt().isBefore(now)){
                pastContest.add(mapper.mapContestDTO(contest));
            }else if(contest.getEndAt().isAfter(now) && contest.getStartAt().isBefore(now)){
                ongoingContest.add(mapper.mapContestDTO(contest));
            }else{
                upcomingContest.add(mapper.mapContestDTO(contest));
            }
        });

        contestSkeleton.setOngoingContests(ongoingContest);
        contestSkeleton.setPastContests(pastContest);
        contestSkeleton.setUpcomingContests(upcomingContest);
        return contestSkeleton;
    }
}
