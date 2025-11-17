package com.kamaleshbala.codesphere.service;


import com.kamaleshbala.codesphere.model.TestcaseModel;
import com.kamaleshbala.codesphere.repository.TestcaseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestcaseService {
    private final TestcaseRepo testcaseRepo;

    @Autowired
    public TestcaseService(TestcaseRepo testcaseRepo){
        this.testcaseRepo = testcaseRepo;
    }
    public TestcaseModel add(TestcaseModel testcase){
        testcase.setResult(testcase.getResult().trim());
        return testcaseRepo.save(testcase);
    }
}
