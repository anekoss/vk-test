package com.vk.redirector.security;


import com.vk.redirector.domain.Role;
import com.vk.redirector.domain.RoleType;
import com.vk.redirector.repository.UserRolesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service("RoleService")
@RequiredArgsConstructor
public class RoleService {
    private final UserRolesRepository userRolesRepository;

    @Transactional
    public boolean hasAnyRole(Role... roles) {
        final Long userId = getCurrentAuthentication().getPrincipal();
        final Set<RoleType> roleTypes =
                userRolesRepository.findUserRolesByUserId(userId);
        for (Role role : roles) {
            if (roleTypes.stream().anyMatch(communityRoleType -> communityRoleType.includes(role))) {
                return true;
            }
        }
        return false;
    }

    private static PlainAuthentication getCurrentAuthentication() {
        return (PlainAuthentication) SecurityContextHolder.getContext().getAuthentication();
    }
}
