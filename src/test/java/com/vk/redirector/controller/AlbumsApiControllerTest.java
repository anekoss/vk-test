package com.vk.redirector.controller;

import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import com.vk.redirector.dto.AlbumsRequest;
import com.vk.redirector.dto.AlbumsResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AlbumsApiControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;


    @RegisterExtension
    static WireMockExtension wireMockServer = WireMockExtension.newInstance()
            .options(wireMockConfig().dynamicPort())
            .build();


    @Test
    public void testGetAlbum() {
        String responseBody = "{\"userId\": 1, \"id\": 1, \"title\": \"quidem molestiae enim\"}";
        wireMockServer.stubFor(get(urlEqualTo("/albums/1"))
                .willReturn(aResponse().withStatus(200).withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).withBody(responseBody)));
        AlbumsResponse excepted = new AlbumsResponse(1L, "quidem molestiae enim", 1L);
        ResponseEntity<AlbumsResponse> response = testRestTemplate.getForEntity(wireMockServer.baseUrl() + "/albums/1", AlbumsResponse.class);
        assertThat(response.getStatusCode().is2xxSuccessful()).isEqualTo(true);
        assertThat(response.getBody()).isEqualTo(excepted);
    }

    @Test
    public void testDeleteAlbum() {
        wireMockServer.stubFor(delete(urlEqualTo("/albums/1"))
                .willReturn(aResponse().withStatus(200).withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).withBody("{}")));
        ResponseEntity<String> response = testRestTemplate.exchange(wireMockServer.baseUrl() + "/albums/1", HttpMethod.DELETE, HttpEntity.EMPTY, String.class);
        assertThat(response.getStatusCode().is2xxSuccessful()).isEqualTo(true);
        assertThat(response.getBody()).isEqualTo("{}");
    }

    @Test
    public void testUpdateAlbum() {
        String requestBody = "{\"userId\": 1, \"id\": 1, \"title\": \"quidem molestiae enim\"}";
        AlbumsRequest request = new AlbumsRequest(1L, 1L, "quidem molestiae enim");
        AlbumsResponse excepted = new AlbumsResponse(1L, "quidem molestiae enim", 1L);
        wireMockServer.stubFor(put(urlEqualTo("/albums/1"))
                .withRequestBody(equalToJson(requestBody))
                .willReturn(aResponse().withStatus(200).withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).withBody(requestBody)));
        ResponseEntity<AlbumsResponse> response = testRestTemplate.exchange(wireMockServer.baseUrl() + "/albums/1", HttpMethod.PUT, new HttpEntity<>(request), AlbumsResponse.class);
        assertThat(response.getStatusCode().is2xxSuccessful()).isEqualTo(true);
        assertThat(response.getBody()).isEqualTo(excepted);
    }

    @Test
    public void testPostAlbum() {
        String requestBody = "{\"userId\": 1, \"id\": 1, \"title\": \"quidem molestiae enim\"}";
        AlbumsRequest request = new AlbumsRequest(1L, 1L, "quidem molestiae enim");
        AlbumsResponse excepted = new AlbumsResponse(1L, "quidem molestiae enim", 1L);
        wireMockServer.stubFor(post(urlEqualTo("/albums"))
                .withRequestBody(equalToJson(requestBody))
                .willReturn(aResponse().withStatus(200).withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).withBody(requestBody)));
        ResponseEntity<AlbumsResponse> response = testRestTemplate.postForEntity(wireMockServer.baseUrl() + "/albums", request, AlbumsResponse.class);
        assertThat(response.getStatusCode().is2xxSuccessful()).isEqualTo(true);
        assertThat(response.getBody()).isEqualTo(excepted);
    }


}
