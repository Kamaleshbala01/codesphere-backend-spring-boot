package com.kamaleshbala.codesphere.controller;

import com.kamaleshbala.codesphere.DTO.ContestDTO;
import com.kamaleshbala.codesphere.DTO.ContestSkeletonDTO;
import com.kamaleshbala.codesphere.model.ContestModel;
import com.kamaleshbala.codesphere.model.ProblemModel;
import com.kamaleshbala.codesphere.service.ContestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/contest")
public class ContestController {

    private final ContestService contestService;

    @Autowired
    public ContestController(ContestService contestService) {
        this.contestService = contestService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<ContestModel> getContest(@PathVariable String id) {
        try {
            System.out.println("enter : contest get");
            return new ResponseEntity<>(contestService.getContest(id), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    // getting overall contest List upcoming, ongoing and pastContest

    @GetMapping("/all")
    public ResponseEntity<ContestSkeletonDTO> getContestSkeleton() {
        return new ResponseEntity<>(contestService.getContestSkeleton(), HttpStatus.OK);
    }

    @GetMapping("/skeleton/{id}")
    public ResponseEntity<ContestDTO> getContestDTO(@PathVariable String id){
        return new ResponseEntity<>(contestService.getContestDTO(id),HttpStatus.OK);
    }


    @PostMapping("/create")
    public ResponseEntity<ContestModel> createContest(@RequestBody ContestModel contest) {
        return new ResponseEntity<>(contestService.createContest(contest), HttpStatus.CREATED);
    }

    @PostMapping("/computeScore")
    public ResponseEntity<ContestModel> computeScore(@RequestBody ContestModel contest) {
        return new ResponseEntity<>(contest,HttpStatus.CREATED);
    }

    @PatchMapping("/addProblem/{id}")
    public ResponseEntity<ContestModel> addProblem(@RequestBody ProblemModel problem, @PathVariable String id) {
        return new ResponseEntity<>(contestService.addProblem(id,problem),HttpStatus.OK);
    }
}
