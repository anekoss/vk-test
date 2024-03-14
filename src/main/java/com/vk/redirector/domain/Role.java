package com.vk.redirector.domain;

import java.util.Set;
import static com.vk.redirector.domain.RoleType.ROLE_ADMIN;

public interface Role {

    boolean includes(Role role);

    static Set<Role> roots() {
        return Set.of(ROLE_ADMIN);
    }
}
