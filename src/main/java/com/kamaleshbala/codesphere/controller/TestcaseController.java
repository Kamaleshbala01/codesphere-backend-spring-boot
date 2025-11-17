package com.kamaleshbala.codesphere.controller;


import com.kamaleshbala.codesphere.model.TestcaseModel;
import com.kamaleshbala.codesphere.service.TestcaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/testcase")
public class TestcaseController {

    private final TestcaseService testcaseService;

    @Autowired
    public TestcaseController(TestcaseService testcaseService) {
        this.testcaseService = testcaseService;
    }

    @PostMapping
    public ResponseEntity<TestcaseModel> addTestcase(@RequestBody TestcaseModel testcase) {
        return new ResponseEntity<>(testcaseService.add(testcase), HttpStatus.CREATED);
    }

}
