package com.vk.redirector.controller;

import com.vk.redirector.dto.AddPostsRequest;
import com.vk.redirector.dto.PostsRequest;
import com.vk.redirector.service.PostsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/posts/{id}")
@RequiredArgsConstructor
@PreAuthorize("isAuthenticated()")
public class PostsApiController {
    private final PostsService postsService;

    @GetMapping
    ResponseEntity<?> getPost(@PathVariable("id") Long id) {
        return postsService.getPost(id);
    }

    @DeleteMapping
    ResponseEntity<?> deletePost(@PathVariable("id") Long id) {
        return postsService.deletePost(id);
    }

    @PostMapping
    ResponseEntity<?> createPost(@PathVariable("id") Long id, @RequestBody AddPostsRequest request) {
        return postsService.addPost(id, request);
    }

    @PutMapping
    ResponseEntity<?> updatePost(@PathVariable("id") Long id, @RequestBody PostsRequest request) {
        return postsService.updatePost(id, request);
    }
}
