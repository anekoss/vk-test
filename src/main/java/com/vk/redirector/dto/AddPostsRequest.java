package com.vk.redirector.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AddPostsRequest(@NotNull Long userId, @NotBlank String title, @NotBlank String body) {
}
