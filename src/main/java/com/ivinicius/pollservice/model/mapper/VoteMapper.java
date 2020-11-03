package com.ivinicius.pollservice.model.mapper;

import com.ivinicius.pollservice.api.v1.request.VoteRequest;
import com.ivinicius.pollservice.model.entity.Vote;

public class VoteMapper {

    public static Vote map(VoteRequest from){
        return Vote.builder()
                .pollId(from.getPollId())
                .taxId(from.getTaxId())
                .vote(from.getVote())
                .build();
    }
}
