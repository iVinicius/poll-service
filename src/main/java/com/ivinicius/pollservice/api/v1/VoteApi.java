package com.ivinicius.pollservice.api.v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ivinicius.pollservice.api.v1.request.VoteRequest;
import com.ivinicius.pollservice.api.v1.response.VoteResponse;
import com.ivinicius.pollservice.model.mapper.VoteMapper;
import com.ivinicius.pollservice.service.VoteService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/v1/vote")
@AllArgsConstructor
public class VoteApi {

    private VoteService voteService;

    private ObjectMapper objectMapper;

    @PostMapping("/poll/{pollId}")
    @ApiOperation(value = "Vota em uma eleicao", notes = "Vota SIM ou NAO em uma eleicao aberta. Somente CPFs validos.", response = VoteResponse.class)
    public Mono<VoteResponse> vote(@PathVariable("pollId") String pollId,
                                   @Valid @RequestBody VoteRequest voteRequest) {
        log.info("[PROCESSING] Vote. Request: {}", voteRequest);

        return voteService.vote(pollId, VoteMapper.map(voteRequest))
                .map(vote -> objectMapper.convertValue(vote, VoteResponse.class))
                .doOnSuccess(vote -> log.info("[SUCCESSFUL] Vote. Request: {}", voteRequest))
                .doOnError(throwable -> log.info("[FAILED] Vote. Request: {} {}", voteRequest, throwable.getMessage()));
    }
}
