package com.kamaleshbala.codesphere.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.kamaleshbala.codesphere.enums.ViolationType;
import jakarta.persistence.*;

@Entity
@Table(name = "violation-details")
public class ViolationDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    public ViolationType getViolationType() {
        return violationType;
    }

    public void setViolationType(ViolationType violationType) {
        this.violationType = violationType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ViolationModel getViolation() {
        return violation;
    }

    public void setViolation(ViolationModel violation) {
        this.violation = violation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "voilation_id", nullable = false)
    @JsonBackReference(value = "violation-details")
    private ViolationModel violation;

    @Enumerated(EnumType.STRING)
    private ViolationType violationType;

    @Column(columnDefinition = "TEXT")
    private String description;
}
