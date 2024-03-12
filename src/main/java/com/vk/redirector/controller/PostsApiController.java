package com.vk.redirector.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/posts")
public class PostsApiController {

    @GetMapping(path = "/{id}")
    ResponseEntity<?> getPost(@PathVariable("id") Long userId) {
        log.info("get users");
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = "/{id}")
    ResponseEntity<?> deletePost(@PathVariable("id") Long userId) {
        log.info("get users");
        return ResponseEntity.ok().build();
    }

    @PostMapping
    ResponseEntity<?> createPost(@PathVariable("id") Long userId) {
        log.info("get users");
        return ResponseEntity.ok().build();
    }

    @PutMapping
    ResponseEntity<?> updatePost(@PathVariable("id") Long userId) {
        log.info("get users");
        return ResponseEntity.ok().build();
    }
}
