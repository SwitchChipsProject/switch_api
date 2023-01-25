package com.switch_proj.api.api.exception.handler;

import com.switch_proj.api.api.exception.dto.BaseException;
import com.switch_proj.api.api.exception.dto.ExceptionEnum;
import com.switch_proj.api.api.exception.dto.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> ApiException(BaseException e) {
        log.error("API Exception",e);
        return new ResponseEntity<>(response(e.getCode(), e.getMessage()), e.getCode().getStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response> MethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String errorMessage = e.getFieldError().getDefaultMessage();
        ExceptionEnum code = ExceptionEnum.REQUEST_PARAMETER_INVALID;
        return new ResponseEntity<>(response(code, errorMessage), code.getStatus());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Response> MethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        log.error("Exception",e);
        ExceptionEnum code = ExceptionEnum.REQUEST_PARAMETER_MISSING;
        return new ResponseEntity<>(response(code, code.getReason()), code.getStatus());
    }

    Response response(ExceptionEnum code, String message) {
        return Response.builder()
                .code(code.getCode())
                .description(message)
                .build();
    }

}

