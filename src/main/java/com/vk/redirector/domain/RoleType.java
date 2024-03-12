package com.vk.redirector.domain;

import java.util.Set;

public enum RoleType implements Role {
    ROLE_POSTS_VIEWER, ROLE_POSTS_EDITOR(Set.of(ROLE_POSTS_VIEWER)), ROLE_ALBUMS_VIEWER, ROLE_ALBUMS_EDITOR(Set.of(ROLE_ALBUMS_VIEWER)),
    ROLE_USERS_VIEWER, ROLE_USERS_EDITOR(Set.of(ROLE_USERS_VIEWER)), ROLE_POSTS_MODERATOR(Set.of(ROLE_POSTS_VIEWER, ROLE_POSTS_EDITOR)),
    ROLE_ALBUMS_MODERATOR(Set.of(ROLE_ALBUMS_VIEWER, ROLE_ALBUMS_EDITOR)), ROLE_USERS_MODERATOR(Set.of(ROLE_USERS_VIEWER, ROLE_USERS_EDITOR)),
    ROLE_ADMIN(Set.of(ROLE_USERS_MODERATOR, ROLE_POSTS_MODERATOR, ROLE_ALBUMS_MODERATOR));

    private final Set<Role> childrenRoles;

    RoleType(Set<Role> childrenRoles) {
        this.childrenRoles = childrenRoles;
    }

    RoleType() {
        this.childrenRoles = Set.of();
    }

    @Override
    public boolean includes(Role role) {
        return this.equals(role) || childrenRoles.stream().anyMatch(r -> r.includes(role));
    }
}
