package com.vk.redirector.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AlbumsResponse(@JsonProperty("userId") Long userId, @JsonProperty("title") String title,
                             @JsonProperty("id") Long id) {
}
