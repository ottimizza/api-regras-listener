package br.com.ottimizza.regraslistener.domain.exception;

import feign.FeignException;
import java.nio.file.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.ottimizza.regraslistener.domain.response.ErrorResponse;

import org.springframework.http.HttpStatus;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

import java.util.Locale;
import org.apache.kafka.common.KafkaException;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

@ControllerAdvice
public class ExceptionHandling {

    @ExceptionHandler({ RuntimeException.class })
    public HttpEntity<?> handleRunTimeException(RuntimeException e, Locale locale) {
        return error(INTERNAL_SERVER_ERROR, "internal_server_error", e.getMessage(), e);
    }

    @ExceptionHandler({ AccessDeniedException.class })
    public HttpEntity<?> handleAccessDeniedException(AccessDeniedException e, Locale locale) {
        return error(BAD_REQUEST, "access_denied", e.getMessage(), e);
    }

    @ExceptionHandler({ IllegalArgumentException.class })
    public HttpEntity<?> handleIllegalArgumentException(RuntimeException e, Locale locale) {
        return error(BAD_REQUEST, "illegal_arguments", e.getMessage(), e);
    }

    @ExceptionHandler(FeignException.class)
    public HttpEntity<?> handleFeignException(FeignException e) {
        return error(HttpStatus.valueOf(e.status()), "internal_server_error", e.contentUTF8(), e);
    }

    @ExceptionHandler(KafkaException.class)
    public HttpEntity<?> handleKafkaException(KafkaException e) {
        return error(INTERNAL_SERVER_ERROR, "internal_server_error", e.getMessage(), e);
    }

    @ExceptionHandler(FeignException.GatewayTimeout.class)
    public HttpEntity<?> handleFeignGatewayTimeoutException(FeignException e) {
        return error(HttpStatus.valueOf(e.status()), "gateway_timeout", e.contentUTF8(), e);
    }

    @ExceptionHandler(FeignException.BadRequest.class)
    public HttpEntity<?> handleFeignBadRequestException(FeignException e) {
        return error(HttpStatus.valueOf(e.status()), "bad_request", e.contentUTF8(), e);
    }

    @ExceptionHandler(FeignException.NotFound.class)
    public HttpEntity<?> handleFeignNotFoundException(FeignException e) {
        return error(HttpStatus.valueOf(e.status()), "not_found", e.contentUTF8(), e);
    }

    @ExceptionHandler(FeignException.TooManyRequests.class)
    public HttpEntity<?> handleFeignTooManyRequestsException(FeignException e) {
        return error(HttpStatus.valueOf(e.status()), "too_many_requests", e.contentUTF8(), e);
    }

    private HttpEntity<?> error(HttpStatus status, String error, String errorDescription, Exception e) {
        ErrorResponse response = new ErrorResponse(error, errorDescription);
        return ResponseEntity.status(status).body(response);
    }
}