package com.kamaleshbala.codesphere.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.kamaleshbala.codesphere.enums.ExecutionStatus;
import jakarta.persistence.*;

@Entity
@Table(name = "executedtestcases")
public class ExecutedTestcaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @ManyToOne
    @JoinColumn(name = "testcase_id", nullable = false)
    @JsonBackReference(value = "testcase-executedTestcase")
    private TestcaseModel testcase;

    @ManyToOne
    @JoinColumn(name = "submission_id",nullable = false)
    @JsonBackReference(value = "submission-executedTestcase")
    private SubmissionModel submission;

    public SubmissionModel getSubmission() {
        return submission;
    }

    public void setSubmission(SubmissionModel submission) {
        this.submission = submission;
    }

    public ExecutionStatus getStatus() {
        return status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TestcaseModel getTestcase() {
        return testcase;
    }

    public void setTestcase(TestcaseModel testcase) {
        this.testcase = testcase;
    }

    public String getUserOutput() {
        return userOutput;
    }

    public void setUserOutput(String userOutput) {
        this.userOutput = userOutput;
    }

    public ExecutionStatus isStatus() {
        return status;
    }

    public void setStatus(ExecutionStatus status) {
        this.status = status;
    }

    @Column(columnDefinition = "TEXT")
    private String userOutput;
    @Enumerated(EnumType.STRING)
    private ExecutionStatus status;
}
