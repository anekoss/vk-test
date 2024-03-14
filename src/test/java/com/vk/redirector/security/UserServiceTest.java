package com.vk.redirector.security;

import com.vk.redirector.repository.RoleRepository;
import com.vk.redirector.repository.UserRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class UserServiceTest {
    private final UserRepository userRepository = Mockito.mock(UserRepository.class);

    private final RoleRepository roleRepository = Mockito.mock(RoleRepository.class);

    private final UserService userService = new UserService(Mockito.mock(EntityManager.class), userRepository, roleRepository, Mockito.mock(BCryptPasswordEncoder.class));


    @Test
    void testLoadUserByUsernameShouldThrowException() {
        when(userRepository.findByUsername("admin")).thenReturn(Optional.empty());
        UsernameNotFoundException exception = assertThrows(UsernameNotFoundException.class, () -> userService.loadUserByUsername("admin"));
        assertThat(exception.getMessage()).isEqualTo("User not found");
    }

}
