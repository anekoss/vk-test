package com.vk.redirector.audit;

import com.vk.redirector.entity.Audit;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.time.OffsetDateTime;


@Component
@RequiredArgsConstructor
public class AuditInterceptor implements HandlerInterceptor {

    private final AuditService auditService;
    private final String timeAttributeName = "startTime";


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        long startTime = System.currentTimeMillis();
        request.setAttribute(timeAttributeName, startTime);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) {
        Audit auditLog = new Audit();
        auditLog.setTimestamp(OffsetDateTime.now());
        auditLog.setUsername(request.getRemoteUser());
        auditLog.setAccess(response.getStatus() != HttpStatus.FORBIDDEN.value());
        auditLog.setRequestParams(request.getQueryString());
        auditLog.setUri(request.getRequestURI());
        auditLog.setResponseCode(response.getStatus());
        auditService.save(auditLog);
    }
}
