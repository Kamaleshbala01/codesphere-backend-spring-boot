package com.kamaleshbala.codesphere.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "contestvoilations")
public class ViolationModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "user_id" ,nullable = false)
    @JsonBackReference(value = "user-violation")
    private UserModel user;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public ContestModel getContest() {
        return contest;
    }

    public void setContest(ContestModel contest) {
        this.contest = contest;
    }

    public List<ViolationDetails> getViolationDetails() {
        return violationDetails;
    }

    public void setViolationDetails(List<ViolationDetails> violationDetails) {
        this.violationDetails = violationDetails;
    }

    @ManyToOne
    @JoinColumn(name = "contest_id",nullable = false)
    @JsonBackReference(value = "contest-violation")
    private ContestModel contest;

    @OneToMany(mappedBy = "violation",cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "violation-details")
    private List<ViolationDetails> violationDetails = new ArrayList<>();
}
