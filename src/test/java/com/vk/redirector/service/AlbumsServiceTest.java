package com.vk.redirector.service;

import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import com.vk.redirector.dto.AddAlbumsRequest;
import com.vk.redirector.dto.AlbumsRequest;
import com.vk.redirector.dto.AlbumsResponse;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.assertj.core.api.Assertions.assertThat;


@RequiredArgsConstructor
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AlbumsServiceTest {
    private final WebClient webClient = WebClient.create(wireMockServer.baseUrl());
    private final AlbumsService albumsService = new AlbumsService(webClient);


    @RegisterExtension
    static WireMockExtension wireMockServer = WireMockExtension.newInstance()
            .options(wireMockConfig().dynamicPort())
            .build();


    @Test
    public void testGetAlbum() throws IOException {
        String responseBody = "{\"userId\": 1, \"id\": 1, \"title\": \"quidem molestiae enim\"}";
        wireMockServer.stubFor(get(urlEqualTo("/albums/1"))
                .willReturn(aResponse().withStatus(200).withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).withBody(responseBody)));
        AlbumsResponse excepted = new AlbumsResponse(1L, "quidem molestiae enim", 1L);
        assertThat(albumsService.getAlbum(1L)).isEqualTo(excepted);
    }

    @Test
    public void testDeleteAlbums() throws IOException {
        wireMockServer.stubFor(delete(urlEqualTo("/albums/1"))
                .willReturn(aResponse().withStatus(200).withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).withBody("{}")));
        assertThat(albumsService.deleteAlbum(1L)).isEqualTo("{}");
    }

    @Test
    public void testUpdateAlbum() throws IOException {
        String requestBody = "{\"userId\": 1, \"id\": 1, \"title\": \"quidem molestiae enim\"}";
        AlbumsRequest request = new AlbumsRequest(1L, 1L, "quidem molestiae enim");
        AlbumsResponse response = new AlbumsResponse(1L, "quidem molestiae enim", 1L);
        wireMockServer.stubFor(put(urlEqualTo("/albums/1"))
                .withRequestBody(equalToJson(requestBody))
                .willReturn(aResponse().withStatus(200).withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).withBody(requestBody)));
        assertThat(albumsService.updateAlbum(1L, request)).isEqualTo(response);
    }

    @Test
    public void testCreateAlbum() {
        String requestBody = "{\"userId\": 1, \"title\": \"quidem molestiae enim\"}";
        String responseBody = "{\"userId\": 1, \"id\": 1, \"title\": \"quidem molestiae enim\"}";
        AddAlbumsRequest request = new AddAlbumsRequest(1L, "quidem molestiae enim");
        AlbumsResponse response = new AlbumsResponse(1L, "quidem molestiae enim", 1L);
        wireMockServer.stubFor(post(urlEqualTo("/albums"))
                .withRequestBody(equalToJson(requestBody))
                .willReturn(aResponse().withStatus(201).withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).withBody(responseBody)));
        assertThat(albumsService.addAlbum(request)).isEqualTo(response);
    }


}