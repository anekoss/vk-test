package com.vk.redirector.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AddAlbumsRequest(@JsonProperty("userId") Long userId, @JsonProperty("title") String title) {
}
