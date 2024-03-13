package com.vk.redirector.repository;

import com.vk.redirector.domain.RoleType;
import com.vk.redirector.entity.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface UserRolesRepository extends JpaRepository<UserRoles, Long> {

    Set<RoleType> findUserRolesByUserId(Long id);

    Set<RoleType> findUserRolesByUserUsername(String username);



}
