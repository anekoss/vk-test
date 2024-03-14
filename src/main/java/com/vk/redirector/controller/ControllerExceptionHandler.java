package com.vk.redirector.controller;

import org.springdoc.api.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ControllerExceptionHandler {


    @ExceptionHandler({AuthenticationServiceException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Invalid username or password")
    public ErrorMessage badRequestParameter(AuthenticationServiceException exception, WebRequest request) {
        return new ErrorMessage(exception.getMessage());
    }

    @ExceptionHandler({AccessDeniedException.class})
    @ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "User doesn't have privilege")
    public ErrorMessage badRequestParameter(AccessDeniedException exception, WebRequest request) {
        return new ErrorMessage(exception.getMessage());
    }
}
