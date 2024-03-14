package com.vk.redirector.security;

import com.vk.redirector.entity.User;
import com.vk.redirector.repository.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DbAuthenticationProviderTest {

    private final UserRepository userRepository = Mockito.mock(UserRepository.class);

    private final DbAuthenticationProvider dbAuthenticationProvider = new DbAuthenticationProvider(userRepository);

    private static User user;

    @BeforeAll
    public static void init() {
        user = new User();
        user.setUsername("admin");
        user.setPassword("admin");
    }

    @Test
    public void testAuthenticate_ValidUser() {
        Mockito.when(userRepository.findByUsername("admin")).thenReturn(Optional.of(user));
        user.setId(1L);
        Authentication authentication = new UsernamePasswordAuthenticationToken("admin", "admin");
        assertThat(dbAuthenticationProvider.authenticate(authentication).isAuthenticated()).isEqualTo(true);
    }

    @Test
    public void testAuthenticate_InvalidUser() {
        Mockito.when(userRepository.findByUsername("user")).thenReturn(Optional.empty());
        Authentication authentication = new UsernamePasswordAuthenticationToken("admin", "admin");
        AuthenticationServiceException exception = assertThrows(AuthenticationServiceException.class, () -> dbAuthenticationProvider.authenticate(authentication));
        assertThat(exception.getMessage()).isEqualTo("Not authenticate user");
    }

    @Test
    public void testAuthenticate_IncorrectPassword() {
        Mockito.when(userRepository.findByUsername("admin")).thenReturn(Optional.of(user));
        Authentication authentication = new UsernamePasswordAuthenticationToken("admin", "wrongPassword");
        AuthenticationServiceException exception = assertThrows(AuthenticationServiceException.class, () -> dbAuthenticationProvider.authenticate(authentication));
        assertThat(exception.getMessage()).isEqualTo("Invalid username or password");
    }
}
