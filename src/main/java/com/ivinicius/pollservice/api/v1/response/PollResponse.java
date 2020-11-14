package com.ivinicius.pollservice.api.v1.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(onlyExplicitlyIncluded = true)
public class PollResponse {

    @ApiModelProperty("Id da votacao")
    @ToString.Include
    private String pollId;
    @ApiModelProperty("Data e hora do inicio da votacao")
    @ToString.Include
    private LocalDateTime startingTime;
    @ApiModelProperty("Data e hora do final da votacao")
    @ToString.Include
    private LocalDateTime endingTime;
}
