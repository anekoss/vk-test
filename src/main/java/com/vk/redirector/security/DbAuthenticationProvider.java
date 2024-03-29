package com.vk.redirector.security;


import com.vk.redirector.entity.User;
import com.vk.redirector.repository.UserRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
@RequiredArgsConstructor
class DbAuthenticationProvider implements AuthenticationProvider {
    private final UserRepository userRepository;

    @Override
    public Authentication authenticate(@NotNull Authentication authentication) throws AuthenticationException {
        final var password = authentication.getCredentials().toString();
        Optional<User> optionalUser = userRepository.findByUsername(authentication.getName());
        if (optionalUser.isEmpty()) {
            throw new AuthenticationServiceException("Not authenticate user");
        }
        if (!optionalUser.get().getPassword().equals(password)) {
            throw new AuthenticationServiceException("Invalid username or password");
        }
        return userRepository.findByUsername(authentication.getName())
                .map(user -> new PlainAuthentication(user.getUsername()))
                .orElseThrow(() -> new AuthenticationServiceException("Invalid username or password"));
    }


    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.equals(authentication);
    }
}