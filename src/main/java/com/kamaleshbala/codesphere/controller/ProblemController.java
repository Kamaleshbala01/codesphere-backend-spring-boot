package com.kamaleshbala.codesphere.controller;

import com.kamaleshbala.codesphere.model.ProblemModel;
import com.kamaleshbala.codesphere.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/problemset")
public class ProblemController {

    private final ProblemService problemService;

    @Autowired
    public ProblemController(ProblemService problemService) {
        this.problemService = problemService;
    }

    @PostMapping
    public ResponseEntity<ProblemModel> add(@RequestBody ProblemModel problem) {
        return new ResponseEntity<>(problemService.add(problem), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProblemModel> getProblem(@PathVariable String id) {
        return new ResponseEntity<>(problemService.getProblemWithSampleTestcase(id),HttpStatus.OK);
    }
}
