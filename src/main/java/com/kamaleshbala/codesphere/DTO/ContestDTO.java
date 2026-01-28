package com.kamaleshbala.codesphere.DTO;

import java.time.Instant;
import java.time.LocalDate;

public class ContestDTO {
    private String id;
    private String title;
    private Instant startDate;
    private int duration;
    private long startIn;

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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public long getStartIn() {
        return startIn;
    }

    public void setStartIn(long startIn) {
        this.startIn = startIn;
    }

    public Instant getStartDate() {
        return startDate;
    }

    public void setStartDate(Instant startDate) {
        this.startDate = startDate;
    }
}
