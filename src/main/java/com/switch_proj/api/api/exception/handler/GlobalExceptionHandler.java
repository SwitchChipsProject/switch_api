package com.switch_proj.api.api.exception.handler;

import com.switch_proj.api.api.auth.utils.JwtTokenProvider;
import com.switch_proj.api.api.exception.dto.ExceptionEnum;
import com.switch_proj.api.api.exception.dto.Response;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.util.WebUtils;


import javax.servlet.ServletException;

@RestControllerAdvice
public class GlobalExceptionHandler  {
    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response> MethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String errorMessage = e.getFieldError().getDefaultMessage();
        ExceptionEnum code = ExceptionEnum.REQUEST_PARAMETER_INVALID;
        return new ResponseEntity<>(response(code, errorMessage), code.getStatus());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Response> MethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        logger.error("Exception",e);
        ExceptionEnum code = ExceptionEnum.REQUEST_PARAMETER_MISSING;
        return new ResponseEntity<>(response(code, code.getReason()), code.getStatus());
    }


    @ExceptionHandler(ServletException.class)
    protected ResponseEntity<Object> handleNestedServletException(Exception ex, WebRequest request) {
        request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
        ExceptionEnum code = ExceptionEnum.RESPONSE_INTERNAL_SEVER_ERROR;

        logger.error("Servlet Exception", ex);
        return new ResponseEntity<>(response(code, ex.getMessage()), code.getStatus());
    }
    @ExceptionHandler(Exception.class)
    @Nullable
    public final ResponseEntity<Object> handleOtherException(Exception ex, WebRequest request) throws Exception {
        request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
        ExceptionEnum code = ExceptionEnum.RESPONSE_INTERNAL_SEVER_ERROR;
        HttpHeaders headers = new HttpHeaders();

        logger.error("Unknown Internal Exception Occurred", ex);
        return new ResponseEntity<>(response(code,ex.getMessage()), headers, code.getStatus());
    }

    Response response(ExceptionEnum code, String message) {
        return Response.builder()
                .code(code.getCode())
                .description(message)
                .build();
    }

}

