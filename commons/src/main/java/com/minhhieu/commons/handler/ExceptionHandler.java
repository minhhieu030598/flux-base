package com.minhhieu.commons.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.minhhieu.commons.exception.BusinessErrorCode;
import com.minhhieu.commons.exception.BusinessException;
import com.minhhieu.commons.exception.FieldViolation;
import com.minhhieu.commons.exception.ValidationException;
import com.minhhieu.commons.util.ErrorCode;
import com.minhhieu.commons.model.response.Response;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@Order(-2)
@Log4j2
public class ExceptionHandler implements WebExceptionHandler {
    private final ObjectMapper objectMapper;

    public ExceptionHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    @SuppressWarnings("all")
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        log.error(ex);
        var response = exchange.getResponse();
        if (ex instanceof ValidationException ve) {
            return handle(ve, response);
        } else if (ex instanceof BusinessException be) {
            return writeBody(response, be.getErrorCode(), be.getMessage(), null);
        } else {
            exchange.getResponse().setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return exchange.getResponse().setComplete();
    }

    private Mono<Void> handle(ValidationException ve, ServerHttpResponse response) {
        var fieldViolations = ve.getErrors().stream()
                .map(error -> new FieldViolation(((FieldError) error).getField(), error.getDefaultMessage()))
                .toList();
        return writeBody(response, ErrorCode.INVALID_PARAMETERS, ve.getMessage(), fieldViolations);
    }

    @SneakyThrows
    private Mono<Void> writeBody(ServerHttpResponse response, BusinessErrorCode errorCode, String message, List<FieldViolation> errors) {
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        response.setRawStatusCode(errorCode.getHttpStatus());
        var bufferFactory = response.bufferFactory();
        var body = Response.ofFailed(errorCode, message, errors);
        var buffer = bufferFactory.wrap(objectMapper.writeValueAsBytes(body));
        return response.writeWith(Mono.just(buffer));
    }

}
