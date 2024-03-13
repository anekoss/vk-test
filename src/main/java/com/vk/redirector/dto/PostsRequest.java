package com.vk.redirector.dto;

public record PostsRequest(Long userId, Long id, String title, String body) {
}
