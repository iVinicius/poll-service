package com.ivinicius.pollservice.api.v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ivinicius.pollservice.model.entity.Vote;
import com.ivinicius.pollservice.service.VoteService;
import com.ivinicius.pollservice.stubs.VoteRequestStub;
import com.ivinicius.pollservice.stubs.VoteResponseStub;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class VoteApiTest {

    @Mock
    private VoteService voteService;
    @Mock
    private ObjectMapper objectMapper;

    private WebTestClient webTestClient;

    @Before
    public void setUp(){
        webTestClient = WebTestClient
                .bindToController(new VoteApi(voteService, objectMapper))
                .configureClient()
                .build();
    }

    @Test
    public void shouldUpdateAccountSuccessfully() {
        String voteId = "vote-id-hehe";
        String pollId = "poll-id-rss";
        when(voteService.vote(any(), any()))
                .thenReturn(Mono.just(Vote.builder().build()));

        when(objectMapper.convertValue(any(), any(Class.class))).thenReturn(VoteResponseStub.create(voteId, pollId));

        webTestClient.post()
                .uri(uriBuilder -> uriBuilder.path("/v1/vote/poll")
                        .pathSegment("poll-id-rss")
                        .build())
                .bodyValue(VoteRequestStub.create())
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo(voteId)
                .jsonPath("$.pollId").isEqualTo(pollId)
                .jsonPath("$.vote").isEqualTo("YES");
    }
}
