package com.ivinicius.pollservice.api.v1.request;

import com.ivinicius.pollservice.enums.VoteEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VoteRequest {

    @ApiModelProperty("Id da votacao")
    @NotNull
    private VoteEnum vote;
    @ApiModelProperty("Id da votacao")
    @Size(min = 11, max = 11, message = "CPF deve ter 11 digitos")
    @NotNull
    private String taxId;
}
