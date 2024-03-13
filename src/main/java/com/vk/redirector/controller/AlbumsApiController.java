package com.vk.redirector.controller;

import com.vk.redirector.dto.AddAlbumsRequest;
import com.vk.redirector.dto.AlbumsRequest;
import com.vk.redirector.dto.AlbumsResponse;
import com.vk.redirector.service.AlbumsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AlbumsApiController {
    private final AlbumsService albumsService;

    @GetMapping(path = "/api/albums/{id}")
    @PreAuthorize("isAuthenticated()")
    ResponseEntity<AlbumsResponse> getAlbums(@PathVariable("id") Long id) {
        return albumsService.getAlbum(id);
    }

    @DeleteMapping(path = "/api/albums/{id}")
    ResponseEntity<Void> deleteAlbums(@PathVariable("id") Long id) {
        return albumsService.deleteAlbum(id);
    }

    @PostMapping(path = "/api/albums")
    ResponseEntity<AlbumsResponse> createAlbums(@RequestBody AddAlbumsRequest request) {
        return albumsService.addAlbum(request);
    }

    @PutMapping(path = "/api/albums/{id}")
    ResponseEntity<AlbumsResponse> updateAlbums(@PathVariable("id") Long id, @RequestBody AlbumsRequest request) {
        return albumsService.updateAlbum(id, request);
    }
}
