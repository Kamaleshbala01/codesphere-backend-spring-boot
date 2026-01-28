package com.kamaleshbala.codesphere.controller;

import com.kamaleshbala.codesphere.DTO.ViolationDTO;
import com.kamaleshbala.codesphere.model.ViolationModel;
import com.kamaleshbala.codesphere.service.ViolationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/voilation")
public class ViolationController {
    ViolationService violationService;
    
    public ViolationController(ViolationService violationService) {
        this.violationService = violationService;
    }
    @PostMapping()
    public ResponseEntity<ViolationModel> addViolation( @RequestBody ViolationDTO violation){
        return new ResponseEntity<>(violationService.addViolation(violation), HttpStatus.CREATED);
    }

    @GetMapping("/canAttend/{id}")
    public ResponseEntity<Boolean> getViolationById(@PathVariable String id){
        return new ResponseEntity<>(violationService.canAttend(id),HttpStatus.OK);
    }

    @GetMapping("/contest/{id}")
    public ResponseEntity<ViolationModel> getViolationByContest(@PathVariable String id){
        return new ResponseEntity<>(violationService.getViolation(id),HttpStatus.OK);
    }
}
