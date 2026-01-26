package com.kamaleshbala.codesphere.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "contests")
public class ContestModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public List<ProblemModel> getProblems() {
        return problems;
    }

    public void setProblems(List<ProblemModel> problems) {
        this.problems = problems;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Instant getStartAt() {
        return startAt;
    }

    public void setStartAt(Instant startAt) {
        this.startAt = startAt;
    }

    public Instant getEndAt() {
        return endAt;
    }

    public void setEndAt(Instant endAt) {
        this.endAt = endAt;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    @JsonBackReference(value = "user-contest")
    private UserModel user;

    @ManyToMany
    @JoinTable(
            name = "contest_problems",
            joinColumns = @JoinColumn(name = "contest_id"),
            inverseJoinColumns = @JoinColumn(name = "problem_id")
    )
    private List<ProblemModel> problems;

//    @OneToMany(mappedBy = "contests", cascade = CascadeType.ALL, orphanRemoval = true)
//    @JsonManagedReference(value = "contest-violation")
//    List<ViolationModel> violations;

    private Instant startAt;
    private Instant endAt;
    private Duration duration;
}
