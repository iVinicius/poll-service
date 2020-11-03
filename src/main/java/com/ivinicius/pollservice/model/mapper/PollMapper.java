package com.ivinicius.pollservice.model.mapper;

import com.ivinicius.pollservice.model.entity.Poll;

import java.time.LocalDateTime;
import java.util.Optional;

public class PollMapper {

    public static Poll map(String subjectId, Long durationMinutes){
        return Poll.builder()
                .subjectId(subjectId)
                .startingTime(LocalDateTime.now())
                .endingTime(Optional.ofNullable(durationMinutes)
                        .map(plus -> LocalDateTime.now().plusMinutes(durationMinutes))
                        .orElse(LocalDateTime.now().plusMinutes(1)))
                .build();
    }
}
