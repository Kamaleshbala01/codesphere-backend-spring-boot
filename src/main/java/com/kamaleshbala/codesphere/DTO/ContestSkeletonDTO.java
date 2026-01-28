package com.kamaleshbala.codesphere.DTO;

import java.util.List;

public class ContestSkeletonDTO {
    private List<ContestDTO> upcomingContests;
    private List<ContestDTO> ongoingContests;
    private List<ContestDTO> pastContests;

    public List<ContestDTO> getUpcomingContests() {
        return upcomingContests;
    }

    public void setUpcomingContests(List<ContestDTO> upcomingContests) {
        this.upcomingContests = upcomingContests;
    }

    public List<ContestDTO> getPastContests() {
        return pastContests;
    }

    public void setPastContests(List<ContestDTO> pastContests) {
        this.pastContests = pastContests;
    }

    public List<ContestDTO> getOngoingContests() {
        return ongoingContests;
    }

    public void setOngoingContests(List<ContestDTO> ongoingContests) {
        this.ongoingContests = ongoingContests;
    }
}
