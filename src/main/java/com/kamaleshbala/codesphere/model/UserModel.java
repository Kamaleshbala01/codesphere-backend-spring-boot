package com.kamaleshbala.codesphere.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(unique = true, nullable = false)
    private String username;
    private String password;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "user-contest")
    private List<ContestModel> contests;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "user-submission")
    private List<SubmissionModel> submissions;

//    public List<ViolationModel> getViolations() {
//        return violations;
//    }
//
//    public void setViolations(List<ViolationModel> violations) {
//        this.violations = violations;
//    }
//
//    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL, orphanRemoval = true)
//    @JsonManagedReference(value = "user-violation")
//    List<ViolationModel> violations;

    public String getId() {
        return id;
    }

    public List<SubmissionModel> getSubmissions() {
        return submissions;
    }

    public void setSubmissions(List<SubmissionModel> submissions) {
        this.submissions = submissions;
    }

    public List<ContestModel> getContests() {
        return contests;
    }

    public void setContests(List<ContestModel> contests) {
        this.contests = contests;
    }

    public void setId(String  id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
