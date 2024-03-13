package com.vk.redirector.audit;

import com.vk.redirector.entity.Audit;
import com.vk.redirector.repository.AuditRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuditService {

    private final AuditRepository auditLogRepository;

    public void save(Audit auditLog) {

        auditLogRepository.save(auditLog);
    }
}
