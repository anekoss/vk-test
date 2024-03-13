package com.vk.redirector.controller;

import com.vk.redirector.dto.AddPostsRequest;
import com.vk.redirector.dto.PostsRequest;
import com.vk.redirector.dto.PostsResponse;
import com.vk.redirector.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/api/posts")
@RequiredArgsConstructor
@PreAuthorize("isAuthenticated()")
public class PostsApiController {
    private final PostsService postsService;

    @GetMapping(path = "/{id}")
    @PreAuthorize("@RoleService.hasAnyRole(T(com.vk.redirector.domain.RoleType).ROLE_POSTS_VIEWER)")
    PostsResponse getPost(@PathVariable("id") Long id) {
        return postsService.getPost(id);
    }

    @DeleteMapping(path = "/{id}")
    @PreAuthorize("@RoleService.hasAnyRole( T(com.vk.redirector.domain.RoleType).ROLE_POSTS_MODERATOR)")
    String deletePost(@PathVariable("id") Long id) {
        return postsService.deletePost(id);
    }

    @PostMapping
    @PreAuthorize("@RoleService.hasAnyRole(T(com.vk.redirector.domain.RoleType).ROLE_POSTS_MODERATOR)")
    PostsResponse createPost(@RequestBody AddPostsRequest request) {
        return postsService.addPost(request);
    }

    @PutMapping(path = "/{id}")
    @PreAuthorize("@RoleService.hasAnyRole( T(com.vk.redirector.domain.RoleType).ROLE_POSTS_EDITOR)")
    PostsResponse updatePost(@PathVariable("id") Long id, @RequestBody PostsRequest request) {
        return postsService.updatePost(id, request);
    }
}
