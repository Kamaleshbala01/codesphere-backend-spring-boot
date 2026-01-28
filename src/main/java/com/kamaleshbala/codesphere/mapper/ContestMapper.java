package com.kamaleshbala.codesphere.mapper;

import com.kamaleshbala.codesphere.DTO.ContestDTO;
import com.kamaleshbala.codesphere.model.ContestModel;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;

public class ContestMapper {

    public ContestDTO mapContestDTO(ContestModel contest){
        ContestDTO contestDTO = new ContestDTO();
        contestDTO.setId(contest.getId());
        contestDTO.setTitle(contest.getTitle());
        contestDTO.setStartDate(contest.getStartAt());
        contestDTO.setDuration((int) contest.getDuration().toMinutes());
        Instant now = Instant.now();
        if(contest.getStartAt().isBefore(now)){
            contestDTO.setStartIn(0);
        }else{
            Duration duration = Duration.between(now, contest.getStartAt());
            contestDTO.setStartIn(duration.toMinutes());
        }
        return contestDTO;
    }
}
