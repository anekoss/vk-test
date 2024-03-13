package com.vk.redirector.service;

import com.vk.redirector.dto.AddAlbumsRequest;
import com.vk.redirector.dto.AlbumsRequest;
import com.vk.redirector.dto.AlbumsResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@AllArgsConstructor
public class AlbumsService {
    private final WebClient webClient;
    private final String requestUri = "/albums/{id}";

    public AlbumsResponse addAlbum(AddAlbumsRequest request) {
        return webClient
                .post()
                .uri("/albums")
                .body(Mono.just(request), AddAlbumsRequest.class)
                .retrieve()
                .bodyToMono(AlbumsResponse.class)
                .block();
    }

    public String deleteAlbum(Long id) {
        return webClient.delete().uri(requestUri, id).retrieve().bodyToMono(String.class).block();
    }

    public AlbumsResponse updateAlbum(Long id, AlbumsRequest request) {
        return webClient
                .put()
                .uri(requestUri, id)
                .body(Mono.just(request), AlbumsRequest.class)
                .retrieve()
                .bodyToMono(AlbumsResponse.class)
                .block();
    }

    public AlbumsResponse getAlbum(Long id) {
        return webClient.get().uri(requestUri, id).retrieve().bodyToMono(AlbumsResponse.class).block();
    }


}
