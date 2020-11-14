package com.ivinicius.pollservice.api.v1.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubjectRequest {

    @ApiModelProperty("Descricao da pauta.")
    @NotNull
    private String description;
}
