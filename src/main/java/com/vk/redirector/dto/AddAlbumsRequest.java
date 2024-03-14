package com.vk.redirector.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AddAlbumsRequest(@JsonProperty("userId") @NotNull Long userId,
                               @JsonProperty("title") @NotBlank String title) {
}
