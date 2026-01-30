package com.kamaleshbala.codesphere.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.kamaleshbala.codesphere.enums.Language;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "submissions")
public class SubmissionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "contest_id",nullable = false)
    @JsonBackReference(value = "submission-contest")
    private ContestModel contest;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    @JsonBackReference(value = "user-submission")
    private UserModel user;

    @ManyToOne
    @JoinColumn(name = "problem_id", nullable = false)
    @JsonBackReference(value = "problem-submission")
    private ProblemModel problem;

    @OneToMany(mappedBy = "submission",cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "submission-executedTestcase")
    private List<ExecutedTestcaseModel> executedTestcases = new ArrayList<>();

    @Column(columnDefinition = "TEXT")
    private String code;

    private int totalScore = 0;

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    @Enumerated(EnumType.STRING)
    private Language language;

    private int passedTestcases = 0;

    public int getPassedTestcases() {
        return passedTestcases;
    }

    public void setPassedTestcases(int passedTestcases) {
        this.passedTestcases = passedTestcases;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<ExecutedTestcaseModel> getExecutedTestcases() {
        return executedTestcases;
    }

    public void setExecutedTestcases(List<ExecutedTestcaseModel> executedTestcases) {
        this.executedTestcases = executedTestcases;
    }

    public ContestModel getContest() {
        return contest;
    }

    public void setContest(ContestModel contest) {
        this.contest = contest;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public ProblemModel getProblem() {
        return problem;
    }

    public void setProblem(ProblemModel problem) {
        this.problem = problem;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
