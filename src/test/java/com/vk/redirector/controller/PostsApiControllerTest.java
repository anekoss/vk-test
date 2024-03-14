package com.vk.redirector.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import com.vk.redirector.dto.PostsRequest;
import com.vk.redirector.dto.PostsResponse;
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
public class PostsApiControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    private final Path responsePath = Path.of("src/test/java/com/vk/redirector/service/response/post_response.json");
    private final Path addRequestPath = Path.of("src/test/java/com/vk/redirector/service/response/add_post_request.json");


    @RegisterExtension
    static WireMockExtension wireMockServer = WireMockExtension.newInstance()
            .options(wireMockConfig().dynamicPort())
            .build();


    @Test
    public void testGetAlbum() throws IOException {
        String responseBody = Files.readString(responsePath);
        wireMockServer.stubFor(get(urlEqualTo("/posts/1"))
                .willReturn(aResponse().withStatus(200).withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).withBody(responseBody)));
        ResponseEntity<PostsResponse> response = testRestTemplate.getForEntity(wireMockServer.baseUrl() + "/posts/1", PostsResponse.class);
        assertThat(response.getStatusCode().is2xxSuccessful()).isEqualTo(true);
        assertThat(response.getBody()).isEqualTo(new ObjectMapper().readValue(responseBody, PostsResponse.class));
    }

    @Test
    public void testDeleteAlbum() {
        wireMockServer.stubFor(delete(urlEqualTo("/posts/1"))
                .willReturn(aResponse().withStatus(200).withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).withBody("{}")));
        ResponseEntity<String> response = testRestTemplate.exchange(wireMockServer.baseUrl() + "/posts/1", HttpMethod.DELETE, HttpEntity.EMPTY, String.class);
        assertThat(response.getStatusCode().is2xxSuccessful()).isEqualTo(true);
        assertThat(response.getBody()).isEqualTo("{}");
    }

    @Test
    public void testUpdateAlbum() throws IOException {
        String requestBody = Files.readString(responsePath);
        wireMockServer.stubFor(put(urlEqualTo("/posts/1"))
                .withRequestBody(equalToJson(requestBody))
                .willReturn(aResponse().withStatus(200).withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).withBody(requestBody)));
        ResponseEntity<PostsResponse> response = testRestTemplate.exchange(wireMockServer.baseUrl() + "/posts/1", HttpMethod.PUT, new HttpEntity<>(new ObjectMapper().readValue(requestBody, PostsResponse.class)), PostsResponse.class);
        assertThat(response.getStatusCode().is2xxSuccessful()).isEqualTo(true);
        assertThat(response.getBody()).isEqualTo(new ObjectMapper().readValue(requestBody, PostsResponse.class));
    }

    @Test
    public void testPostAlbum() throws IOException {
        String requestBody = Files.readString(addRequestPath);
        String responseBody = Files.readString(responsePath);
        wireMockServer.stubFor(post(urlEqualTo("/posts"))
                .withRequestBody(equalToJson(requestBody))
                .willReturn(aResponse().withStatus(200).withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).withBody(responseBody)));
        ResponseEntity<PostsResponse> response = testRestTemplate.exchange(wireMockServer.baseUrl() + "/posts", HttpMethod.POST, new HttpEntity<>(requestBody), PostsResponse.class);
        assertThat(response.getStatusCode().is2xxSuccessful()).isEqualTo(true);
        assertThat(response.getBody()).isEqualTo(new ObjectMapper().readValue(responseBody, PostsResponse.class));
    }


}


