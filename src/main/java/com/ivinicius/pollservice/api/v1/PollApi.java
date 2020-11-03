package com.ivinicius.pollservice.api.v1;

import com.ivinicius.pollservice.api.v1.response.ResultResponse;
import com.ivinicius.pollservice.service.PollService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/v1/poll")
@AllArgsConstructor
public class PollApi {

    private PollService pollService;

    @GetMapping(value = "/{pollId}/result")
    @ApiOperation(value = "Resultado de uma eleicao", notes = "Retorna o resultado de uma eleicao e divulga no rabbit.", response = Long.class)
    public Mono<ResultResponse> result(@PathVariable String pollId){
        log.info("[PROCESSING] Results. PollId: {}", pollId);

        return pollService.result(pollId)
                .doOnSuccess(vote -> log.info("[SUCCESSFUL] Results. PollId: {}", pollId))
                .doOnError(throwable -> log.info("[FAILED] Results. PollId: {}", pollId));
    }
}
