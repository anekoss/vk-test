package com.vk.redirector.repository;

import com.vk.redirector.entity.Audit;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditRepository extends JpaRepository<Audit, Long> {
    @Override
    Audit save(@NotNull Audit entity);
}
