package com.vk.redirector.controller;

import com.vk.redirector.dto.AddUsersRequest;
import com.vk.redirector.dto.UsersRequest;
import com.vk.redirector.dto.UsersResponse;
import com.vk.redirector.service.UsersService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/api/users")
@RequiredArgsConstructor
@PreAuthorize("isAuthenticated()")
public class UsersApiController {
    private final UsersService usersService;

    @GetMapping(path = "/{id}")
    @PreAuthorize("@RoleService.hasAnyRole( T(com.vk.redirector.domain.RoleType).ROLE_USERS_VIEWER)")
    UsersResponse getUser(@NotNull @PathVariable("id") Long id) {
        return usersService.getUser(id);
    }

    @DeleteMapping(path = "/{id}")
    @PreAuthorize("@RoleService.hasAnyRole( T(com.vk.redirector.domain.RoleType).ROLE_USERS_MODERATOR)")
    String deleteUser(@NotNull @PathVariable("id") Long id) {
        return usersService.deleteUser(id);
    }

    @PostMapping
    @PreAuthorize("@RoleService.hasAnyRole( T(com.vk.redirector.domain.RoleType).ROLE_USERS_MODERATOR)")
    UsersResponse createUser(@NotNull @RequestBody AddUsersRequest request) {
        return usersService.addUser(request);
    }

    @PutMapping(path = "/{id}")
    @PreAuthorize("@RoleService.hasAnyRole( T(com.vk.redirector.domain.RoleType).ROLE_USERS_EDITOR)")
    UsersResponse updateUser(@NotNull @PathVariable("id") Long id, @NotNull @RequestBody UsersRequest request) {
        return usersService.updateUser(id, request);
    }

}
