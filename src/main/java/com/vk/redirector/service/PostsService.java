package com.vk.redirector.service;

import com.vk.redirector.dto.AddPostsRequest;
import com.vk.redirector.dto.PostsRequest;
import com.vk.redirector.dto.PostsResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@AllArgsConstructor
public class PostsService {
    private final WebClient webClient;
    private final String requestUri = "/posts/{id}";

    public PostsResponse addPost(AddPostsRequest request) {
        return webClient
                .post()
                .uri("/posts")
                .body(Mono.just(request), AddPostsRequest.class)
                .retrieve()
                .bodyToMono(PostsResponse.class).block();
    }

    public String deletePost(Long id) {
        return webClient
                .delete()
                .uri(requestUri, id)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    public PostsResponse updatePost(Long id, PostsRequest request) {
        return webClient
                .put()
                .uri(requestUri, id)
                .body(Mono.just(request), PostsRequest.class)
                .retrieve()
                .bodyToMono(PostsResponse.class)
                .block();
    }

    public PostsResponse getPost(Long id) {
        return webClient
                .get()
                .uri(requestUri, id)
                .retrieve()
                .bodyToMono(PostsResponse.class)
                .block();
    }


}
