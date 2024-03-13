package com.vk.redirector.audit;

import com.vk.redirector.entity.Audit;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.time.OffsetDateTime;

@Component
@RequiredArgsConstructor
public class AuditInterceptor implements HandlerInterceptor {

    private final AuditService auditService;
    private final String timeAttribiteName = "startTime";


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        long startTime = System.currentTimeMillis();
        request.setAttribute(timeAttribiteName, startTime);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        Audit auditLog = new Audit();
        auditLog.setTimestamp(OffsetDateTime.now());
        auditLog.setUsername(request.getRemoteUser()); // Получение имени пользователя
        auditLog.setHasAccess(request.isUserInRole("ROLE_ADMIN")); // Проверка доступа
        auditLog.setRequestParams(request.getQueryString());
        auditLog.setUri(request.getRequestURI());
        auditLog.setExecutionTime(System.currentTimeMillis() - (long) request.getAttribute(timeAttribiteName));
        auditLog.setResponseCode(response.getStatus());
        auditService.save(auditLog);
    }
}
