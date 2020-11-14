package com.ivinicius.pollservice.service;

import com.ivinicius.pollservice.exception.SubjectNotFoundException;
import com.ivinicius.pollservice.model.entity.Poll;
import com.ivinicius.pollservice.model.entity.Subject;
import com.ivinicius.pollservice.repository.SubjectRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SubjectServiceTest {

    @InjectMocks
    private SubjectService subjectService;

    @Mock
    private SubjectRepository subjectRepository;

    @Mock
    private PollService pollService;

    @Test
    public void testCreateSubjectSuccess() {
        subjectService.createSubject(Subject.builder().id("xx").build());

        verify(subjectRepository, times(1)).save(any());
    }

    @Test
    public void testCreatePollSuccess() {
        when(subjectRepository.findById("cpx")).thenReturn(Mono.just(Subject.builder().build()));
        when(pollService.createPoll("cpx", 5l)).thenReturn(Mono.just(Poll.builder().build()));

        StepVerifier.create(subjectService.createPoll("cpx", 5l))
                .expectNextMatches(a -> {
                    verify(subjectRepository, times(1)).findById("cpx");
                    verify(pollService, times(1)).createPoll("cpx", 5l);
                    return true;
                })
                .verifyComplete();
    }

    @Test
    public void testCreatePollSubjectNotFoundException() {
        when(subjectRepository.findById("cpx")).thenReturn(Mono.empty());

        StepVerifier.create(subjectService.createPoll("cpx", 5l))
                .verifyError(SubjectNotFoundException.class);
    }
}
