package com.vk.redirector.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static com.vk.redirector.domain.RoleType.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class RoleTypeTest {

    @Test
    void testShouldNotThrowStackOverflowException() {
        final var roots = Role.roots();
        List<RoleType> roles = List.of(ROLE_USERS_VIEWER, ROLE_POSTS_EDITOR, ROLE_ALBUMS_MODERATOR);
        assertDoesNotThrow(
                () -> {
                    for (Role root : roots) {
                        for (var roleToCheck : roles) {
                            root.includes(roleToCheck);
                        }
                    }
                }
        );
    }

    @ParameterizedTest
    @MethodSource("provideDataForTest")
    void testShouldIncludeOrNotTheGivenRoles(Role root, Set<Role> roles, boolean excepted) {
        for (Role role : roles) {
            assertThat(root.includes(role)).isEqualTo(excepted);
        }
    }

    private static Stream<Arguments> provideDataForTest() {
        return Stream.of(
                Arguments.of(
                        ROLE_ADMIN,
                        Set.of(ROLE_ALBUMS_MODERATOR, ROLE_POSTS_MODERATOR, ROLE_USERS_MODERATOR),
                        true
                ),
                Arguments.of(ROLE_USERS_MODERATOR,
                        Set.of(ROLE_USERS_VIEWER, ROLE_USERS_EDITOR),
                        true),
                Arguments.of(ROLE_USERS_VIEWER, Set.of(ROLE_USERS_EDITOR), false),
                Arguments.of(ROLE_ALBUMS_MODERATOR, Set.of(ROLE_USERS_VIEWER), false)
        );
    }
}
