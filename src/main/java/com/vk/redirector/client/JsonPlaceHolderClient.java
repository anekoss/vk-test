package com.vk.redirector.client;

import org.springframework.web.reactive.function.client.WebClient;

public class JsonPlaceHolderClient {
    private final String defaultUrl = "https://jsonplaceholder.typicode.com/";
    private final WebClient webClient;

    public JsonPlaceHolderClient(String url) {
        this.webClient = WebClient.builder().baseUrl(url).build();
    }

    public JsonPlaceHolderClient() {
        this.webClient = WebClient.builder().baseUrl(defaultUrl).build();
    }


}
