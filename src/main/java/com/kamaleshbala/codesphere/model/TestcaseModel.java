package com.kamaleshbala.codesphere.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.kamaleshbala.codesphere.enums.Testcase;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "testcases")
public class TestcaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(columnDefinition = "MEDIUMTEXT")
    private String testcase;

    @Column(columnDefinition = "TEXT")
    private String result;

    @Enumerated(EnumType.STRING)
    private Testcase type;

    private int score;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getTestcase() {
        return testcase;
    }

    public void setTestcase(String testcase) {
        this.testcase = testcase;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Testcase getType() {
        return type;
    }

    public void setType(Testcase type) {
        this.type = type;
    }

    public ProblemModel getProblem() {
        return problem;
    }

    public void setProblem(ProblemModel problem) {
        this.problem = problem;
    }

    @ManyToOne
    @JoinColumn(name = "problem_id" ,nullable = false)
    @JsonBackReference(value = "problem-testcase")
    private ProblemModel problem;

    @OneToMany(mappedBy = "testcase",cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonManagedReference(value = "testcase-executedTestcase")
    @JsonIgnore
    private List<ExecutedTestcaseModel> executedTestcases = new ArrayList<>();

    public List<ExecutedTestcaseModel> getExecutedTestcases() {
        return executedTestcases;
    }

    public void setExecutedTestcases(List<ExecutedTestcaseModel> executedTestcases) {
        this.executedTestcases = executedTestcases;
    }
}
