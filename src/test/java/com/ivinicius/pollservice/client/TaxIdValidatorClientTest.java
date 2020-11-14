package com.ivinicius.pollservice.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.ivinicius.pollservice.client.response.TaxValidatorResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.test.util.ReflectionTestUtils;
import wiremock.org.apache.http.HttpHeaders;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class TaxIdValidatorClientTest {

    private TaxIdValidatorClient taxIdValidatorClient;
    private WireMockServer wireMockServer = new WireMockServer(wireMockConfig().port(9000));

    @Before
    public void setUp() {
        this.taxIdValidatorClient = new TaxIdValidatorClient();
        ReflectionTestUtils.setField(taxIdValidatorClient, "baseUrl", "http://localhost:9000");

        this.wireMockServer.start();
    }

    @After
    public void tearDown() {
        this.wireMockServer.stop();
    }

    @Test
    public void shouldGetAbleToVote() throws JsonProcessingException {
        wireMockServer.stubFor(get(urlPathEqualTo("/users/45678912385"))
                .willReturn(aResponse()
                        .withHeader(HttpHeaders.ACCEPT, "application/json")
                        .withHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                        .withStatus(HttpStatus.OK.value())
                        .withBody(jsonResponse)));

        TaxValidatorResponse response = taxIdValidatorClient.validateCpf("45678912385").block();

        assertNotNull(response);
    }

    private String jsonResponse = "{\n" +
            "    \"status\": \"UNABLE_TO_VOTE\"\n" +
            "}";
}
