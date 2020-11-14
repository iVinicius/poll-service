package com.ivinicius.pollservice.stubs;

import com.ivinicius.pollservice.api.v1.request.VoteRequest;
import com.ivinicius.pollservice.enums.VoteEnum;

public class VoteRequestStub {

    public static VoteRequest create(){
        return VoteRequest.builder()
                .taxId("85928084036")
                .vote(VoteEnum.YES)
                .build();
    }
}
