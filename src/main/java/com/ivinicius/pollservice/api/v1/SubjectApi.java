package com.ivinicius.pollservice.api.v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ivinicius.pollservice.api.v1.request.SubjectRequest;
import com.ivinicius.pollservice.api.v1.response.PollResponse;
import com.ivinicius.pollservice.model.entity.Subject;
import com.ivinicius.pollservice.model.mapper.SubjectMapper;
import com.ivinicius.pollservice.service.SubjectService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/v1/subject")
@AllArgsConstructor
public class SubjectApi {

    private SubjectService subjectService;

    private ObjectMapper objectMapper;

    @PostMapping
    @ApiOperation(value = "Cria nova pauta", notes = "Cria uma nova pauta e retorna o ID", response = String.class)
    public Mono<String> create(@Valid @RequestBody SubjectRequest subjectRequest){
        log.info("[PROCESSING] Create new subject. Request: {}", subjectRequest);

        return subjectService.createSubject(SubjectMapper.map(subjectRequest))
                .map(Subject::getId)
                .doOnSuccess(aLong -> log.info("[SUCCESSFUL] Create new subject. SubjectId: {}", aLong))
                .doOnError(throwable -> log.info("[FAILED] Create new subject. Request: {}", subjectRequest));
    }

    @PostMapping(value = "/{subjectId}/poll")
    @ApiOperation(value = "Inicia uma votacao", notes = "Inicia uma votacao de um minuto ou ate o tempo especificado", response = PollResponse.class)
    public Mono<PollResponse> createPoll(@PathVariable("subjectId") String subjectId,
                                         @RequestParam(required = false) Long durationMinutes){
        log.info("[PROCESSING] Create new poll. Params: {}, {}", subjectId, durationMinutes);

        return subjectService.createPoll(subjectId, durationMinutes)
                .map(poll -> objectMapper.convertValue(poll, PollResponse.class))
                .doOnSuccess(poll -> log.info("[SUCCESSFUL] Create new poll. Poll: {}", poll))
                .doOnError(throwable -> log.info("[FAILED] Create new poll. Params: {}, {} {}", subjectId, durationMinutes, throwable.getMessage()));
    }

}
