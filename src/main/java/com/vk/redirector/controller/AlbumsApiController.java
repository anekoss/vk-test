package com.vk.redirector.controller;

import com.vk.redirector.dto.AddAlbumsRequest;
import com.vk.redirector.dto.AlbumsRequest;
import com.vk.redirector.dto.AlbumsResponse;
import com.vk.redirector.service.AlbumsService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/api/albums")
@PreAuthorize("isAuthenticated()")
@RequiredArgsConstructor
public class AlbumsApiController {
    private final AlbumsService albumsService;

    @GetMapping(path = "/{id}")
    @PreAuthorize("@RoleService.hasAnyRole( T(com.vk.redirector.domain.RoleType).ROLE_ALBUMS_VIEWER)")
    AlbumsResponse getAlbums(@NotNull @PathVariable("id") Long id) {
        return albumsService.getAlbum(id);
    }

    @DeleteMapping(path = "/{id}")
    @PreAuthorize("@RoleService.hasAnyRole(T(com.vk.redirector.domain.RoleType).ROLE_ALBUMS_MODERATOR)")
    String deleteAlbums(@NotNull @PathVariable("id") Long id) {
        return albumsService.deleteAlbum(id);
    }

    @PostMapping
    @PreAuthorize("@RoleService.hasAnyRole( T(com.vk.redirector.domain.RoleType).ROLE_ALBUMS_MODERATOR)")
    AlbumsResponse createAlbums(@NotNull @RequestBody AddAlbumsRequest request) {
        return albumsService.addAlbum(request);
    }

    @PutMapping(path = "/{id}")
    @PreAuthorize("@RoleService.hasAnyRole( T(com.vk.redirector.domain.RoleType).ROLE_ALBUMS_EDITOR)")
    AlbumsResponse updateAlbums(@NotNull @PathVariable("id") Long id, @NotNull @RequestBody AlbumsRequest request) {
        return albumsService.updateAlbum(id, request);
    }
}
