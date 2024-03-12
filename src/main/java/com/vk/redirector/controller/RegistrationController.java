package com.vk.redirector.controller;

import com.vk.redirector.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@Slf4j
@RestController
@RequiredArgsConstructor
public class RegistrationController {


    @PostMapping("/registration")
    public ResponseEntity<?> registration(WebRequest request) {
        UserDto userDto = new UserDto();
        return ResponseEntity.ok().build();
    }

}
