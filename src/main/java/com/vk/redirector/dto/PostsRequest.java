package com.vk.redirector.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PostsRequest(@NotNull Long userId, @NotNull Long id, @NotBlank String title, @NotBlank String body) {
}
