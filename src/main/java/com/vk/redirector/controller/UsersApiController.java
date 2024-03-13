package com.vk.redirector.controller;

import com.vk.redirector.dto.AddUsersRequest;
import com.vk.redirector.dto.UsersRequest;
import com.vk.redirector.dto.UsersResponse;
import com.vk.redirector.service.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequiredArgsConstructor
public class UsersApiController {
    private final UsersService usersService;

    @GetMapping(path = "/api/users/{id}")
    ResponseEntity<UsersResponse> getUser(@PathVariable("id") Long id) {
        return usersService.getUser(id);
    }

    @DeleteMapping(path = "/api/users/{id}")
    ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        return usersService.deleteUser(id);
    }

    @PostMapping(path = "/api/users")
    ResponseEntity<UsersResponse> createUser(@RequestBody AddUsersRequest request) {
        return usersService.addUser(request);
    }

    @PutMapping(path = "/api/users/{id}")
    ResponseEntity<UsersResponse> updateUser(@PathVariable("id") Long id, @RequestBody UsersRequest request) {
        return usersService.updateUser(id, request);
    }

}
