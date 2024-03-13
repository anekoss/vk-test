package com.vk.redirector.controller;

import com.vk.redirector.dto.AddPostsRequest;
import com.vk.redirector.dto.PostsRequest;
import com.vk.redirector.dto.PostsResponse;
import com.vk.redirector.service.PostsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(path = "/api/posts")
@RequiredArgsConstructor
public class PostsApiController {
    private final PostsService postsService;

    @GetMapping(path = "/{id}")
    PostsResponse getPost(@PathVariable("id") Long id) {
        return postsService.getPost(id);
    }

    @DeleteMapping(path = "/{id}")
    String deletePost(@PathVariable("id") Long id) {
        return postsService.deletePost(id);
    }

    @PostMapping
    PostsResponse createPost(@RequestBody AddPostsRequest request) {
        return postsService.addPost(request);
    }

    @PutMapping(path = "/{id}")
    PostsResponse updatePost(@PathVariable("id") Long id, @RequestBody PostsRequest request) {
        return postsService.updatePost(id, request);
    }
}
