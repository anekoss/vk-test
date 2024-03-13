package com.vk.redirector.controller;

import com.vk.redirector.dto.AddAlbumsRequest;
import com.vk.redirector.dto.AlbumsRequest;
import com.vk.redirector.service.AlbumsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/albums/{id}")
@RequiredArgsConstructor

public class AlbumsApiController {
    private final AlbumsService albumsService;

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    ResponseEntity<?> getAlbums(@PathVariable("id") Long id) {
        return albumsService.getAlbum(id);
    }

    @DeleteMapping
    ResponseEntity<?> deleteAlbums(@PathVariable("id") Long id) {
        return albumsService.deleteAlbum(id);
    }

    @PostMapping
    ResponseEntity<?> createAlbums(@PathVariable("id") Long id, @RequestBody AddAlbumsRequest request) {
        return albumsService.addAlbum(id, request);
    }

    @PutMapping
    ResponseEntity<?> updateAlbums(@PathVariable("id") Long id, @RequestBody AlbumsRequest request) {
        return albumsService.updateAlbum(id, request);
    }
}
