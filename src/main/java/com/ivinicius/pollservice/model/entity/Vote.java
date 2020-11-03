package com.ivinicius.pollservice.model.entity;

import com.ivinicius.pollservice.enums.VoteEnum;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Builder
@Data
public class Vote {

    @Id
    private String id;
    private String pollId;
    private VoteEnum vote;
    private String taxId;
}
