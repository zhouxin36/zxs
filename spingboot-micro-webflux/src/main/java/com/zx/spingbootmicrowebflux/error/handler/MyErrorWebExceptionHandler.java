package com.zx.spingbootmicrowebflux.error.handler;

import com.zx.spingbootmicrowebflux.error.exception.ZXException;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.HttpMessageWriter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.web.reactive.function.server.RequestPredicates.all;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * @author zhouxin
 * @date 2019-02-10
 */
public class MyErrorWebExceptionHandler extends AbstractErrorWebExceptionHandler {

    private final ErrorAttributes errorAttributes;

    public MyErrorWebExceptionHandler(ErrorAttributes errorAttributes
            , ResourceProperties resourceProperties
            , ApplicationContext applicationContext) {
        super(errorAttributes, resourceProperties, applicationContext);
        this.errorAttributes = errorAttributes;
    }

    @Override
    protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
        return route(all(), this::renderErrorResponse);
    }

    private Mono<ServerResponse> renderErrorResponse(ServerRequest request) {

        Exception error = (Exception) this.errorAttributes.getError(request);
        Map<String, Object> map = new HashMap<>();
        map.put("timestamp", LocalDateTime.now());
        map.put("path", request.path());
        Integer status;
        if (error instanceof ZXException) {
            ZXException exception = (ZXException) error;
            status = exception.getCode();
            map.put("status", status);
            map.put("message", exception.getMessage());
        } else {
            HttpStatus httpStatus = determineHttpStatus(error);
            status = httpStatus.value();
            map.put("status", status);
            map.put("message", determineMessage(error));
        }
        return ServerResponse.status(status)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(BodyInserters.fromObject(map));
    }

    private HttpStatus determineHttpStatus(Throwable error) {
        if (error instanceof ResponseStatusException) {
            return ((ResponseStatusException) error).getStatus();
        }
        ResponseStatus responseStatus = AnnotatedElementUtils
                .findMergedAnnotation(error.getClass(), ResponseStatus.class);
        if (responseStatus != null) {
            return responseStatus.code();
        }
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }

    private String determineMessage(Throwable error) {
        if (error instanceof WebExchangeBindException) {
            return error.getMessage();
        }
        if (error instanceof ResponseStatusException) {
            return ((ResponseStatusException) error).getReason();
        }
        ResponseStatus responseStatus = AnnotatedElementUtils
                .findMergedAnnotation(error.getClass(), ResponseStatus.class);
        if (responseStatus != null) {
            return responseStatus.reason();
        }
        return error.getMessage();
    }
}
