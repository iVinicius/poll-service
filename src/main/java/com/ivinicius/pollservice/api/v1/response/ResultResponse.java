package com.ivinicius.pollservice.api.v1.response;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(onlyExplicitlyIncluded = true)
public class ResultResponse {
    @ToString.Include
    private String pollId;
    @ToString.Include
    private Long yesVotes;
    @ToString.Include
    private Long noVotes;
}
