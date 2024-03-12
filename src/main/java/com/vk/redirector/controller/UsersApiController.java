package com.vk.redirector.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/api/users")
public class UsersApiController {

    @GetMapping(path = "/{id}")
    ResponseEntity<?> getUser(@PathVariable("id") Long userId) {
        log.info("get users");
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = "/{id}")
    ResponseEntity<?> deleteUser(@PathVariable("id") Long userId) {
        log.info("get users");
        return ResponseEntity.ok().build();
    }

    @PostMapping
    ResponseEntity<?> createUser(@PathVariable("id") Long userId) {
        log.info("get users");
        return ResponseEntity.ok().build();
    }

    @PutMapping
    ResponseEntity<?> updateUser(@PathVariable("id") Long userId) {
        log.info("get users");
        return ResponseEntity.ok().build();
    }

}
