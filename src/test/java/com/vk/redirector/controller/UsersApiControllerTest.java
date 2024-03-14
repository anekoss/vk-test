package com.vk.redirector.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import com.vk.redirector.dto.UsersResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UsersApiControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    private final Path responsePath = Path.of("src/test/java/com/vk/redirector/service/response/user_response.json");
    private final Path addRequestPath = Path.of("src/test/java/com/vk/redirector/service/response/add_user_request.json");


    @RegisterExtension
    static WireMockExtension wireMockServer = WireMockExtension.newInstance()
            .options(wireMockConfig().dynamicPort())
            .build();


    @Test
    public void testGetUser() throws IOException {
        String responseBody = Files.readString(responsePath);
        wireMockServer.stubFor(get(urlEqualTo("/users/1"))
                .willReturn(aResponse().withStatus(200).withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).withBody(responseBody)));
        ResponseEntity<UsersResponse> response = testRestTemplate.getForEntity(wireMockServer.baseUrl() + "/users/1", UsersResponse.class);
        assertThat(response.getStatusCode().is2xxSuccessful()).isEqualTo(true);
        assertThat(response.getBody()).isEqualTo(new ObjectMapper().readValue(responseBody, UsersResponse.class));
    }

    @Test
    public void testDeleteUser() {
        wireMockServer.stubFor(delete(urlEqualTo("/users/1"))
                .willReturn(aResponse().withStatus(200).withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).withBody("{}")));
        ResponseEntity<String> response = testRestTemplate.exchange(wireMockServer.baseUrl() + "/users/1", HttpMethod.DELETE, HttpEntity.EMPTY, String.class);
        assertThat(response.getStatusCode().is2xxSuccessful()).isEqualTo(true);
        assertThat(response.getBody()).isEqualTo("{}");
    }

    @Test
    public void testUpdateUser() throws IOException {
        String requestBody = Files.readString(responsePath);
        wireMockServer.stubFor(put(urlEqualTo("/users/1"))
                .withRequestBody(equalToJson(requestBody))
                .willReturn(aResponse().withStatus(200).withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).withBody(requestBody)));
        ResponseEntity<UsersResponse> response = testRestTemplate.exchange(wireMockServer.baseUrl() + "/users/1", HttpMethod.PUT, new HttpEntity<>(new ObjectMapper().readValue(requestBody, UsersResponse.class)), UsersResponse.class);
        assertThat(response.getStatusCode().is2xxSuccessful()).isEqualTo(true);
        assertThat(response.getBody()).isEqualTo(new ObjectMapper().readValue(requestBody, UsersResponse.class));
    }

    @Test
    public void testPostUser() throws IOException {
        String requestBody = Files.readString(addRequestPath);
        String responseBody = Files.readString(responsePath);
        wireMockServer.stubFor(post(urlEqualTo("/users"))
                .withRequestBody(equalToJson(requestBody))
                .willReturn(aResponse().withStatus(200).withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).withBody(responseBody)));
        ResponseEntity<UsersResponse> response = testRestTemplate.exchange(wireMockServer.baseUrl() + "/users", HttpMethod.POST, new HttpEntity<>(requestBody), UsersResponse.class);
        assertThat(response.getStatusCode().is2xxSuccessful()).isEqualTo(true);
        assertThat(response.getBody()).isEqualTo(new ObjectMapper().readValue(responseBody, UsersResponse.class));
    }


}

