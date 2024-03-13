package com.vk.redirector.service;


import com.vk.redirector.dto.AddUsersRequest;
import com.vk.redirector.dto.UsersRequest;
import com.vk.redirector.dto.UsersResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@Slf4j
@AllArgsConstructor
public class UsersService {

    private final WebClient webClient;
    private final String requestUri = "/users/{id}";

    public ResponseEntity<UsersResponse> addUser(AddUsersRequest request) {
        return webClient.post().uri("/users").contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request).retrieve().toEntity(UsersResponse.class).block();
    }

    public ResponseEntity<Void> deleteUser(Long id) {
        return webClient.delete().uri(requestUri, id).retrieve().toBodilessEntity().block();
    }

    public ResponseEntity<UsersResponse> updateUser(Long id, UsersRequest request) {
        return webClient.put().uri(requestUri, id).body(request, UsersRequest.class).retrieve().toEntity(UsersResponse.class).block();
    }

    public ResponseEntity<UsersResponse> getUser(Long id) {
        return webClient.get().uri(requestUri, id).retrieve().toEntity(UsersResponse.class).block();
    }


}
