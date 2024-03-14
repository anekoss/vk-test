package com.vk.redirector.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import com.vk.redirector.dto.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.assertj.core.api.Assertions.assertThat;

public class UsersServiceTest {

    private final WebClient webClient = WebClient.create(wireMockServer.baseUrl());
    private final UsersService usersService = new UsersService(webClient);
    private final Path responsePath = Path.of("src/test/java/com/vk/redirector/service/response/user_response.json");
    private final Path addRequestPath = Path.of("src/test/java/com/vk/redirector/service/response/add_user_request.json");

    @RegisterExtension
    static WireMockExtension wireMockServer = WireMockExtension.newInstance()
            .options(wireMockConfig().dynamicPort())
            .build();


    @Test
    public void testAddPost() throws IOException {
        String requestBody = Files.readString(addRequestPath);
        String responseBody = Files.readString(responsePath);
        wireMockServer.stubFor(post(urlEqualTo("/users"))
                .withRequestBody(equalToJson(requestBody))
                .willReturn(aResponse().withStatus(201).withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).withBody(responseBody)));
        AddUsersRequest addUsersRequest = new ObjectMapper().readValue(requestBody, AddUsersRequest.class);
        assertThat(usersService.addUser(addUsersRequest)).isEqualTo(new ObjectMapper().readValue(responseBody, UsersResponse.class));
    }

    @Test
    public void testDeletePost() {
        wireMockServer.stubFor(delete(urlEqualTo("/users/1"))
                .willReturn(aResponse().withStatus(200).withHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE).withBody("{}")));
        assertThat(usersService.deleteUser(1L)).isEqualTo("{}");
    }

    @Test
    public void testUpdatePost() throws IOException {
        String requestBody = Files.readString(responsePath);
        wireMockServer.stubFor(put(urlEqualTo("/users/1"))
                .withRequestBody(equalToJson(requestBody))
                .willReturn(aResponse().withStatus(200).withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).withBody(requestBody)));
        UsersRequest usersRequest = new ObjectMapper().readValue(requestBody, UsersRequest.class);
        assertThat(usersService.updateUser(1L, usersRequest)).isEqualTo(new ObjectMapper().readValue(requestBody, UsersResponse.class));
    }

    @Test
    public void testGetPost() throws IOException {
        String responseBody = Files.readString(responsePath);
        wireMockServer.stubFor(get(urlEqualTo("/users/1"))
                .willReturn(aResponse().withStatus(200).withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).withBody(responseBody)));
        assertThat(usersService.getUser(1L)).isEqualTo(new ObjectMapper().readValue(responseBody, UsersResponse.class));
    }
}
