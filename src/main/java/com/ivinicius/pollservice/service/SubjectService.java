package com.ivinicius.pollservice.service;

import com.ivinicius.pollservice.exception.SubjectNotFoundException;
import com.ivinicius.pollservice.model.entity.Poll;
import com.ivinicius.pollservice.model.entity.Subject;
import com.ivinicius.pollservice.repository.SubjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class SubjectService {

    private SubjectRepository subjectRepository;

    private PollService pollService;

    public Mono<Subject> createSubject(Subject subject){
        return subjectRepository.save(subject);
    }

    public Mono<Poll> createPoll(String pollId, Long durationMinutes){
        return subjectExists(pollId)
                .filter(aBoolean -> aBoolean)
                .flatMap(aBoolean -> pollService.createPoll(pollId, durationMinutes))
                .switchIfEmpty(Mono.error(new SubjectNotFoundException()));
    }

    public Mono<Boolean> subjectExists(String pollId){
        return subjectRepository.existsById(pollId);
    }
}
