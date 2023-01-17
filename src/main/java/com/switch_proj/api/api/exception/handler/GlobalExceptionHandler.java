package com.switch_proj.api.api.exception.handler;

import com.switch_proj.api.api.exception.domain.BaseException;
import com.switch_proj.api.api.exception.domain.ExceptionEnum;
import com.switch_proj.api.api.exception.domain.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    // 모든 예외 잡는 메소드
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> ApiException(BaseException e) {
        log.error("Api Exception " + e.toString());
        e.printStackTrace();
        return new ResponseEntity<>(response(e.getCode(), e.getMessage()), e.getCode().getStatus());
    }

    //@Valid가 붙은 파라미터에 대해 검증 실패시 발생
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response> MethodArgumentNotValidException(MethodArgumentNotValidException e) {

        StringBuilder errorMessage = new StringBuilder();
        errorMessage.append(e.getFieldError().getDefaultMessage());

        ExceptionEnum code = ExceptionEnum.REQUEST_PARAMETER_INVALID;
        return new ResponseEntity<>(response(code, errorMessage.toString()), code.getStatus());
    }

    //enum type 일치하지 않아 binding 못할 경우 발생
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Response> MethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        log.error(e.getMessage());
        e.printStackTrace();
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

