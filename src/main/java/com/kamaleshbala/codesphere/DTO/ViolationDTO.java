package com.kamaleshbala.codesphere.DTO;

import com.kamaleshbala.codesphere.enums.ViolationType;

public class ViolationDTO {
    private ViolationType type;
    private String message;

    public String getContestId() {
        return contestId;
    }

    public void setContestId(String contestId) {
        this.contestId = contestId;
    }

    private String contestId;

    public ViolationType getType() {
        return type;
    }

    public void setType(ViolationType type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
