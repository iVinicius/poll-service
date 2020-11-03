package com.ivinicius.pollservice.api.v1.request;

import com.ivinicius.pollservice.enums.VoteEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VoteRequest {

    private VoteEnum vote;
    private String taxId;
    private String pollId;
}
