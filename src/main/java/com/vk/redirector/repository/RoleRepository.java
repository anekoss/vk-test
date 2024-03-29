package com.vk.redirector.repository;

import com.vk.redirector.domain.RoleType;
import com.vk.redirector.entity.Role;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    @Override
    Optional<Role> findById(@NotNull Long aLong);

    Optional<Role> findRolesByName(@NotNull RoleType type);


}
