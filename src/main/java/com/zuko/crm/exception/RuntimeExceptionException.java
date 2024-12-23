package com.zuko.crm.exception;

import com.zuko.crm.dto.exception.RuntimeExceptionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class RuntimeExceptionException {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(RuntimeException.class)
    public RuntimeExceptionDTO handler(RuntimeException err){
        return new RuntimeExceptionDTO(err.getMessage(), HttpStatus.BAD_REQUEST.toString());
    }
}
