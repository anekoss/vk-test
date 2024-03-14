package com.vk.redirector.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import com.vk.redirector.dto.AddPostsRequest;
import com.vk.redirector.dto.PostsRequest;
import com.vk.redirector.dto.PostsResponse;
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

public class PostsServiceTest {

    private final WebClient webClient = WebClient.create(wireMockServer.baseUrl());
    private final PostsService postsService = new PostsService(webClient);
    private final Path responsePath = Path.of("src/test/java/com/vk/redirector/service/response/post_response.json");
    private final Path addRequestPath = Path.of("src/test/java/com/vk/redirector/service/response/add_post_request.json");

    @RegisterExtension
    static WireMockExtension wireMockServer = WireMockExtension.newInstance()
            .options(wireMockConfig().dynamicPort())
            .build();


    @Test
    public void testAddPost() throws IOException {
        String requestBody = Files.readString(addRequestPath);
        String responseBody = Files.readString(responsePath);
        wireMockServer.stubFor(post(urlEqualTo("/posts"))
                .withRequestBody(equalToJson(requestBody))
                .willReturn(aResponse().withStatus(201).withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).withBody(responseBody)));
        AddPostsRequest addPostsRequest = new ObjectMapper().readValue(requestBody, AddPostsRequest.class);
        assertThat(postsService.addPost(addPostsRequest)).isEqualTo(new ObjectMapper().readValue(responseBody, PostsResponse.class));
    }

    @Test
    public void testDeletePost() {
        wireMockServer.stubFor(delete(urlEqualTo("/posts/1"))
                .willReturn(aResponse().withStatus(200).withHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE).withBody("{}")));
        assertThat(postsService.deletePost(1L)).isEqualTo("{}");
    }

    @Test
    public void testUpdatePost() throws IOException {
        String requestBody = Files.readString(responsePath);
        wireMockServer.stubFor(put(urlEqualTo("/posts/1"))
                .withRequestBody(equalToJson(requestBody))
                .willReturn(aResponse().withStatus(200).withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).withBody(requestBody)));
        PostsRequest postsRequest = new ObjectMapper().readValue(requestBody, PostsRequest.class);
        assertThat(postsService.updatePost(1L, postsRequest)).isEqualTo(new ObjectMapper().readValue(requestBody, PostsResponse.class));
    }

    @Test
    public void testGetPost() throws IOException {
        String responseBody = Files.readString(responsePath);
        wireMockServer.stubFor(get(urlEqualTo("/posts/1"))
                .willReturn(aResponse().withStatus(200).withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).withBody(responseBody)));
        assertThat(postsService.getPost(1L)).isEqualTo(new ObjectMapper().readValue(responseBody, PostsResponse.class));
    }
}
