package com.kamaleshbala.codesphere.controller;
import com.kamaleshbala.codesphere.model.CodeModel;
import com.kamaleshbala.codesphere.model.SubmissionModel;
import com.kamaleshbala.codesphere.service.CodeExecutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/execute")
public class CodeExecutorController {

    private final CodeExecutorService codeExecutorService;
    @Autowired
    public CodeExecutorController(CodeExecutorService codeExecutorService) {
        this.codeExecutorService = codeExecutorService;
    }

    @PostMapping("/code")
    public ResponseEntity<SubmissionModel> executeCode(@RequestBody SubmissionModel submission) throws Exception{
        return new ResponseEntity<>(codeExecutorService.execute(submission), HttpStatus.ACCEPTED);
    }

    @PostMapping("/sample")
    ResponseEntity<CodeModel> executeSampleTestcase(@RequestBody CodeModel code) throws Exception{
        return new ResponseEntity<>(codeExecutorService.runSampleTestCase(code), HttpStatus.ACCEPTED);
    }
}
