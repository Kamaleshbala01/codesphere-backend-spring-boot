package com.kamaleshbala.codesphere.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.kamaleshbala.codesphere.enums.ProblemType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "problemset")
public class ProblemModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String title;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Enumerated(EnumType.STRING)
    private ProblemType type;
    @Column(columnDefinition = "TEXT")
    private String constraints;

    @OneToMany(mappedBy = "problem", cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonManagedReference(value = "problem-testcase")
    private List<TestcaseModel> testcases;

    @OneToMany(mappedBy = "problem",cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonManagedReference(value = "problem-submission")
    @JsonIgnore
    private List<SubmissionModel> submissions;

    public List<SubmissionModel> getSubmissions() {
        return submissions;
    }

    public void setSubmissions(List<SubmissionModel> submissions) {
        this.submissions = submissions;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProblemType getType() {
        return type;
    }

    public void setType(ProblemType type) {
        this.type = type;
    }

    public String getConstraints() {
        return constraints;
    }

    public void setConstraints(String constraints) {
        this.constraints = constraints;
    }

    public List<TestcaseModel> getTestcases() {
        return testcases;
    }

    public void setTestcases(List<TestcaseModel> testcases) {
        this.testcases = testcases;
    }
}
