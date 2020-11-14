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

    public Mono<Poll> createPoll(String subjectId, Long durationMinutes){
        return subjectExists(subjectId)
                .flatMap(subject -> pollService.createPoll(subjectId, durationMinutes));
    }

    private Mono<Subject> subjectExists(String subjectId){
        return subjectRepository.findById(subjectId)
                .switchIfEmpty(Mono.error(new SubjectNotFoundException()));
    }
}
