package ru.exercise.webserver.common.exception;

public class BusinessException extends RuntimeException {
    
    public BusinessException(final String message) {
        super(message);
    }
}
