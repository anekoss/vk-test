package com.vk.redirector.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/posts")
public class AlbumsApiController {

    @GetMapping(path = "/{id}")
    ResponseEntity<?> getAlbums(@PathVariable("id") Long userId) {
        log.info("get users");
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = "/{id}")
    ResponseEntity<?> deleteAlbums(@PathVariable("id") Long userId) {
        log.info("get users");
        return ResponseEntity.ok().build();
    }

    @PostMapping
    ResponseEntity<?> createAlbums(@PathVariable("id") Long userId) {
        log.info("get users");
        return ResponseEntity.ok().build();
    }

    @PutMapping
    ResponseEntity<?> updateAlbums(@PathVariable("id") Long userId) {
        log.info("get users");
        return ResponseEntity.ok().build();
    }
}
