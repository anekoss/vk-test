package com.vk.redirector.security;


import com.vk.redirector.domain.Role;
import com.vk.redirector.entity.User;
import com.vk.redirector.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Service("RoleService")
@RequiredArgsConstructor
public class RoleService {
    private final UserRepository userRepository;

    private static UsernamePasswordAuthenticationToken getCurrentAuthentication() {
        return (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
    }

    @Transactional
    public boolean hasAnyRole(Role... roles) throws AccessDeniedException, UsernameNotFoundException {
        final String userName = getCurrentAuthentication().getName();
        final Optional<User> user = userRepository.findByUsername(userName);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("Not authenticate user");
        }
        Set<com.vk.redirector.entity.Role> roleSet = user.get().getRoles();
        for (Role role : roles) {
            if (roleSet.stream().anyMatch(roleType -> roleType.getName().includes(role))) {
                return true;
            }
        }
        throw new AccessDeniedException("User doesn't have privilege");
    }
}