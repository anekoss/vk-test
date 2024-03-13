package com.vk.redirector.repository;

import com.vk.redirector.domain.RoleType;
import com.vk.redirector.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface RoleRepository extends JpaRepository<Role, Long> {

    @Override
    Optional<Role> findById(Long aLong);


}
