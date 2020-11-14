package com.ivinicius.pollservice.stubs;

import com.ivinicius.pollservice.api.v1.response.VoteResponse;
import com.ivinicius.pollservice.enums.VoteEnum;

public class VoteResponseStub {

    public static VoteResponse create(String voteId, String pollId){
        return VoteResponse.builder()
                .id(voteId)
                .pollId(pollId)
                .vote(VoteEnum.YES)
                .build();
    }
}
