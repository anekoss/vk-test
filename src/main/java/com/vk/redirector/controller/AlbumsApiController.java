package com.vk.redirector.controller;

import com.vk.redirector.dto.AddAlbumsRequest;
import com.vk.redirector.dto.AlbumsRequest;
import com.vk.redirector.dto.AlbumsResponse;
import com.vk.redirector.service.AlbumsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(path = "/api/albums")
@PreAuthorize("isAuthenticated()")
@RequiredArgsConstructor
public class AlbumsApiController {
    private final AlbumsService albumsService;

    @GetMapping(path = "/{id}")
//    @PreAuthorize("@RoleService.hasAnyRole(#id, T(com.vk.redirector.domain.RoleType).ROLE_ALBUMS_VIEWER)")
    AlbumsResponse getAlbums(@PathVariable("id") Long id) {
        return albumsService.getAlbum(id);
    }

    @DeleteMapping(path = "/{id}")
    String deleteAlbums(@PathVariable("id") Long id) {
        return albumsService.deleteAlbum(id);
    }

    @PostMapping
    AlbumsResponse createAlbums(@RequestBody AddAlbumsRequest request) {
        return albumsService.addAlbum(request);
    }

    @PutMapping(path = "/{id}")
    AlbumsResponse updateAlbums(@PathVariable("id") Long id, @RequestBody AlbumsRequest request) {
        return albumsService.updateAlbum(id, request);
    }
}
