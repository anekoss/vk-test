package com.vk.redirector.service;


import com.vk.redirector.dto.AddUsersRequest;
import com.vk.redirector.dto.UsersRequest;
import com.vk.redirector.dto.UsersResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@AllArgsConstructor
public class UsersService {

    private final WebClient webClient;
    private final String requestUri = "/users/{id}";

    public UsersResponse addUser(AddUsersRequest request) {
        return webClient
                .post()
                .uri("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(request), AddUsersRequest.class)
                .retrieve()
                .bodyToMono(UsersResponse.class)
                .block();
    }

    public String deleteUser(Long id) {
        return webClient
                .delete()
                .uri(requestUri, id)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    public UsersResponse updateUser(Long id, UsersRequest request) {
        return webClient
                .put()
                .uri(requestUri, id)
                .body(Mono.just(request), UsersRequest.class)
                .retrieve()
                .bodyToMono(UsersResponse.class)
                .block();
    }

    public UsersResponse getUser(Long id) {
        return webClient
                .get()
                .uri(requestUri, id)
                .retrieve()
                .bodyToMono(UsersResponse.class)
                .block();
    }


}
