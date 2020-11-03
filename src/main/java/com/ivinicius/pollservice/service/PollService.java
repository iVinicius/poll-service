package com.ivinicius.pollservice.service;

import com.ivinicius.pollservice.api.v1.response.ResultResponse;
import com.ivinicius.pollservice.enums.VoteEnum;
import com.ivinicius.pollservice.model.entity.Poll;
import com.ivinicius.pollservice.model.entity.Vote;
import com.ivinicius.pollservice.model.mapper.PollMapper;
import com.ivinicius.pollservice.repository.PollRepository;
import com.ivinicius.pollservice.repository.VoteRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@AllArgsConstructor
public class PollService {

    private PollRepository pollRepository;

    private VoteRepository voteRepository;

    private RabbitProducer rabbitProducer;

    public Mono<Poll> createPoll(String pollId, Long durationMinutes){
        return pollRepository.save(PollMapper.map(pollId, durationMinutes));
    }

    public Mono<Poll> pollExists(String pollId){
        return pollRepository.findById(pollId);
    }

    public Mono<ResultResponse> result(String pollId) {
        return Mono.zip(listOfVotesYes(pollId), listOfVotesNo(pollId))
                .map(args -> ResultResponse.builder()
                        .pollId(pollId)
                        .yesVotes(args.getT1())
                        .noVotes(args.getT2())
                        .build())
                .doOnNext(this::exportResultToTopic);
    }

    private void exportResultToTopic(ResultResponse response){
        rabbitProducer.publish(response.toString(), "poll.results");
    }

    private Mono<Long> listOfVotesYes(String pollId){
        return voteRepository.findAll(Example.of(Vote.builder().pollId(pollId).vote(VoteEnum.YES).build())).count();
    }

    private Mono<Long> listOfVotesNo(String pollId){
        return voteRepository.findAll(Example.of(Vote.builder().pollId(pollId).vote(VoteEnum.NO).build())).count();
    }
}
