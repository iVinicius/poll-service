package com.ivinicius.pollservice.service;

import com.ivinicius.pollservice.client.TaxIdValidatorClient;
import com.ivinicius.pollservice.enums.AbleToVoteEnum;
import com.ivinicius.pollservice.exception.PollDoesntExistsException;
import com.ivinicius.pollservice.exception.UserUnableToVoteException;
import com.ivinicius.pollservice.model.entity.Vote;
import com.ivinicius.pollservice.repository.VoteRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@AllArgsConstructor
public class VoteService {

    private PollService pollService;

    private VoteRepository voteRepository;

    private TaxIdValidatorClient taxIdValidatorClient;

    public Mono<Vote> vote(Vote request){
        return pollService.pollExists(request.getPollId())
                .flatMap(poll -> isUserAbleToVote(request))
                .switchIfEmpty(Mono.error(PollDoesntExistsException::new))
                .flatMap(aBoolean -> voteRepository.save(request));
    }

    private Mono<Boolean> isUserAbleToVote(Vote vote){
        return userAlreadyVoted(vote)
                .flatMap(args -> userHasValidTaxId(vote.getTaxId()))
                .filter(able -> able)
                .switchIfEmpty(Mono.error(UserUnableToVoteException::new));
    }

    private Mono<Vote> userAlreadyVoted(Vote vote){
        return voteRepository.findOne(Example.of(buildExample(vote)));
    }

    private Mono<Boolean> userHasValidTaxId(String taxId){
        return taxIdValidatorClient.validateCpf(taxId)
                .map(taxValidatorResponse -> AbleToVoteEnum.ABLE_TO_VOTE == taxValidatorResponse.getStatus());
    }

    private Vote buildExample(Vote from){
        return Vote.builder()
                .taxId(from.getTaxId())
                .pollId(from.getPollId())
                .build();
    }
}
