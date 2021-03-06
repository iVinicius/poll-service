package com.ivinicius.pollservice.api.v1.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(onlyExplicitlyIncluded = true)
public class ResultResponse {

    @ApiModelProperty("Id da votacao")
    @ToString.Include
    private String pollId;
    @ApiModelProperty("Quantidade de votos 'SIM'")
    @ToString.Include
    private Long yesVotes;
    @ApiModelProperty("Quantidade de votos 'NAO'")
    @ToString.Include
    private Long noVotes;
}
