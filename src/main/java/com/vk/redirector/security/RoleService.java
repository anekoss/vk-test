package com.vk.redirector.security;


import com.vk.redirector.domain.Role;
import com.vk.redirector.entity.User;
import com.vk.redirector.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Service("RoleService")
@RequiredArgsConstructor
public class RoleService {
    private final UserRepository userRepository;

    @Transactional
    public boolean hasAnyRole(Role... roles) {
        final Long id = getCurrentAuthentication().getPrincipal();
        final Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            return false;
        }
        Set<com.vk.redirector.entity.Role> roleSet = user.get().getRoles();
        for (Role role : roles) {
            if (roleSet.stream().anyMatch(roleType -> roleType.getName().includes(role))) {
                return true;
            }
        }
        return false;
    }


    private static PlainAuthentication getCurrentAuthentication() {
        return (PlainAuthentication) SecurityContextHolder.getContext().getAuthentication();
    }
}