package com.ivinicius.pollservice.client.response;

import com.ivinicius.pollservice.enums.AbleToVoteEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaxValidatorResponse {

    private AbleToVoteEnum status;
}
