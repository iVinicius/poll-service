package com.ivinicius.pollservice.model.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Builder
@Data
public class Poll {

    @Id
    private String pollId;
    private LocalDateTime startingTime;
    private LocalDateTime endingTime;
    private String subjectId;
}
