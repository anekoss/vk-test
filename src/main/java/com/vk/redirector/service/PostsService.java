package com.vk.redirector.service;

import com.vk.redirector.dto.AddPostsRequest;
import com.vk.redirector.dto.PostsRequest;
import com.vk.redirector.dto.PostsResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@Slf4j
@AllArgsConstructor
public class PostsService {
    private final WebClient webClient;
    private final String requestUri = "/posts/{id}";

    public ResponseEntity<PostsResponse> addPost(Long id, AddPostsRequest request) {
        return webClient.post().uri(requestUri, id).body(request, AddPostsRequest.class).retrieve().toEntity(PostsResponse.class).block();
    }

    public ResponseEntity<Void> deletePost(Long id) {
        return webClient.delete().uri(requestUri, id).retrieve().toBodilessEntity().block();
    }

    public ResponseEntity<PostsResponse> updatePost(Long id, PostsRequest request) {
        return webClient.put().uri(requestUri, id).body(request, PostsRequest.class).retrieve().toEntity(PostsResponse.class).block();
    }

    public ResponseEntity<PostsResponse> getPost(Long id) {
        return webClient.get().uri(requestUri, id).retrieve().toEntity(PostsResponse.class).block();
    }


}
