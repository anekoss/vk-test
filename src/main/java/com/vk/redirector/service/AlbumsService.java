package com.vk.redirector.service;

import com.vk.redirector.dto.AddAlbumsRequest;
import com.vk.redirector.dto.AlbumsRequest;
import com.vk.redirector.dto.AlbumsResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@Slf4j
@AllArgsConstructor
public class AlbumsService {
    private final WebClient webClient;
    private final String requestUri = "/albums/{id}";

    public ResponseEntity<AlbumsResponse> addAlbum(AddAlbumsRequest request) {
        return webClient.post().uri("/albums").body(request, AddAlbumsRequest.class).retrieve().toEntity(AlbumsResponse.class).block();
    }

    public ResponseEntity<Void> deleteAlbum(Long id) {
        return webClient.delete().uri(requestUri, id).retrieve().toBodilessEntity().block();
    }

    public ResponseEntity<AlbumsResponse> updateAlbum(Long id, AlbumsRequest request) {
        return webClient.put().uri(requestUri, id).body(request, AlbumsRequest.class).retrieve().toEntity(AlbumsResponse.class).block();
    }

    public ResponseEntity<AlbumsResponse> getAlbum(Long id) {
        return webClient.get().uri(requestUri, id).retrieve().toEntity(AlbumsResponse.class).block();
    }


}
