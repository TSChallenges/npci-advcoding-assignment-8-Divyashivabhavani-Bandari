package com.mystore.app.config;

import com.mystore.app.config.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class MyGlobalExceptionHandler {

    // TODO
@ExceptionHandler(NotFoundException.class)
public ResponseEntity<String> getNotFoundExceptionMessage(NotFoundException notFoundException){
return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFoundException.getErrorMessage());
}
}
