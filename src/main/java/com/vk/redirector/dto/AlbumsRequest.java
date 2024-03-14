package com.vk.redirector.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AlbumsRequest(@JsonProperty("userId") @NotBlank Long userId,
                            @JsonProperty("id") @NotNull Long id,
                            @JsonProperty("title") @NotBlank String title) {
}
