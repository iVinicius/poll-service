package com.ivinicius.pollservice.api.v1;

import com.ivinicius.pollservice.api.v1.request.VoteRequest;
import com.ivinicius.pollservice.model.entity.Vote;
import com.ivinicius.pollservice.model.mapper.VoteMapper;
import com.ivinicius.pollservice.service.VoteService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/v1/vote")
@AllArgsConstructor
public class VoteApi {

    private VoteService voteService;

    @PostMapping
    @ApiOperation(value = "Vota em uma eleicao", notes = "Vota SIM ou NAO em uma eleicao aberta. Somente CPFs validos.", response = Long.class)
    public Mono<Vote> vote(@RequestBody VoteRequest voteRequest){
        log.info("[PROCESSING] Vote. Request: {}", voteRequest);

        return voteService.vote(VoteMapper.map(voteRequest))
                .doOnSuccess(vote -> log.info("[SUCCESSFUL] Vote. Request: {}", voteRequest))
                .doOnError(throwable -> log.info("[FAILED] Vote. Request: {} {}", voteRequest, throwable.getMessage()));
    }
}
