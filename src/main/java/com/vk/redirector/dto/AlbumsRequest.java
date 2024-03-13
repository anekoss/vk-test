package com.vk.redirector.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AlbumsRequest(@JsonProperty("userId") Long userId, @JsonProperty("id") Long id, @JsonProperty("title") String title) {
}
