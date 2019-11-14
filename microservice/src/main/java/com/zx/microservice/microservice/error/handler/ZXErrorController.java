package com.zx.microservice.microservice.error.handler;

import com.zx.microservice.microservice.error.exception.ZXException;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @see BasicErrorController
 * @see RequestDispatcher
 * @author zhouxin
 * @date 2019-02-07
 */
@Controller
@RequestMapping("${server.error.path:${error.path:/error}}")
public class ZXErrorController extends AbstractErrorController {

    private final ErrorAttributes errorAttributes;

    public ZXErrorController(ErrorAttributes errorAttributes) {
        super(errorAttributes);
        this.errorAttributes = errorAttributes;
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping
    @ResponseBody
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
        ServletWebRequest servletWebRequest = new ServletWebRequest(request);
        Exception error = (Exception)this.errorAttributes.getError(servletWebRequest);
        Map<String, Object> map = new HashMap<>();
        map.put("timestamp", LocalDateTime.now());
        map.put("path",request.getAttribute(RequestDispatcher.ERROR_REQUEST_URI));
        if(error instanceof ZXException){
            ZXException exception = (ZXException) error;
            map.put("status",exception.getCode());
            map.put("message",exception.getMessage());
            return ResponseEntity.status(exception.getCode()).body(map);
        }else {
            ResponseEntityExceptionHandler responseEntityExceptionHandler = new ResponseEntityExceptionHandler(){};
            try {
                ResponseEntity<Object> objectResponseEntity = responseEntityExceptionHandler
                        .handleException(error, new ServletWebRequest(request));
                Exception body = (Exception) Optional.ofNullable(objectResponseEntity)
                        .map(ResponseEntity::getBody).orElse(new Exception());
                map.put("status",Optional.ofNullable(objectResponseEntity)
                        .map(ResponseEntity::getStatusCodeValue).orElse(HttpStatus.INTERNAL_SERVER_ERROR.value()));
                map.put("message",body.getMessage());
                return new ResponseEntity<>(map, Optional.ofNullable(objectResponseEntity)
                        .map(ResponseEntity::getStatusCode).orElse(HttpStatus.INTERNAL_SERVER_ERROR));
            } catch (Exception e) {
                Integer status = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
                map.put("status",status == null ? HttpStatus.INTERNAL_SERVER_ERROR.value() : status);
                map.put("message",status == null ? e.getMessage() : HttpStatus.valueOf(status).getReasonPhrase());
                return new ResponseEntity<>(map,HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }
}
