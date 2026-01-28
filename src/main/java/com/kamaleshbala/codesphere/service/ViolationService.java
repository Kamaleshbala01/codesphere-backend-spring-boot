package com.kamaleshbala.codesphere.service;

import com.kamaleshbala.codesphere.DTO.ViolationDTO;
import com.kamaleshbala.codesphere.model.ContestModel;
import com.kamaleshbala.codesphere.model.UserPrinciple;
import com.kamaleshbala.codesphere.model.ViolationDetails;
import com.kamaleshbala.codesphere.model.ViolationModel;
import com.kamaleshbala.codesphere.repository.ContestRepo;
import com.kamaleshbala.codesphere.repository.ViolationRepo;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class ViolationService {
    private final ViolationRepo violationRepo;
    private final ContestRepo contestRepo;

    public ViolationService(ViolationRepo violationRepo,ContestRepo contestRepo) {
        this.violationRepo = violationRepo;
        this.contestRepo = contestRepo;
    }
    public ViolationModel addViolation(ViolationDTO violationDTO) {
        UserPrinciple user = (UserPrinciple) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ViolationModel violation = violationRepo.findByUser_idAndContest_id(user.getUserId(),violationDTO.getContestId());
        if(violation == null) {
            violation = new ViolationModel();
            violation.setUser(user.getUser());
            ContestModel contest = contestRepo.findById(violationDTO.getContestId()).get();
            violation.setContest(contest);
        }
        ViolationDetails details = new ViolationDetails();
        details.setDescription(violationDTO.getMessage());
        details.setViolation(violation);
        details.setViolationType(violationDTO.getType());
        violation.getViolationDetails().add(details);

        return violationRepo.save(violation);
    }

    public boolean canAttend(String contestId){
        UserPrinciple user = (UserPrinciple) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ViolationModel violation = violationRepo.findByUser_idAndContest_id(user.getUserId(),contestId);

        return violation == null || violation.getViolationDetails().size() < 2;
    }

    public ViolationModel getViolation(String contestId){
        UserPrinciple user = (UserPrinciple) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ViolationModel violation =  violationRepo.findByUser_idAndContest_id(user.getUserId(),contestId);
        System.out.println("user ID : " + user.getUserId() + " contest ID : "+contestId);
        System.out.println("user ID : " + user.getUserId() + " contest ID : "+contestId);
        return violation;
    }
}
