package com.vk.redirector.security;

import com.vk.redirector.dto.AlbumsResponse;
import com.vk.redirector.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ContextConfiguration
public class AuthorizationTest {

    @Autowired
    private TestRestTemplate rest;
    @Autowired
    private UserService userService;


    @Test
    void shouldReturn401IfUnauthorizedUserTryingToCreateCommunity() {
        var response = rest.getForEntity("/api/albums/1", AlbumsResponse.class);
        assertThat(response.getStatusCode()).isEqualTo(UNAUTHORIZED);
    }


    @Test
    void testShouldGetSuccessfully() {
        User user = new User();
        user.setUsername("test");
        user.setPassword("test");
        userService.saveUser(user);
        var response = rest
                .withBasicAuth("test", "test")
                .getForEntity(
                        "/api/users/1",
                        AlbumsResponse.class
                );
        assertThat(response.getStatusCode().is2xxSuccessful()).isEqualTo(true);
    }

    @Test
    void testShouldReturn403IfUserHasNoAccessToViewTheAlbums() {
        User user = new User();
        user.setUsername("test");
        user.setPassword("test");
        userService.saveUser(user);
        var response = rest
                .withBasicAuth("test", "test")
                .postForEntity(
                        "/api/albums/1",
                        null,
                        AlbumsResponse.class
                );
        assertThat(response.getStatusCode().is4xxClientError()).isEqualTo(true);
    }
}
