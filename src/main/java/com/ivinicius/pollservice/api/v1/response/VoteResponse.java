package com.ivinicius.pollservice.api.v1.response;

import com.ivinicius.pollservice.enums.VoteEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(onlyExplicitlyIncluded = true)
public class VoteResponse {

    @ApiModelProperty("Id do voto")
    @ToString.Include
    private String id;
    @ApiModelProperty("Id da votacao")
    @ToString.Include
    private String pollId;
    @ApiModelProperty("Valor do voto")
    @ToString.Include
    private VoteEnum vote;
}
