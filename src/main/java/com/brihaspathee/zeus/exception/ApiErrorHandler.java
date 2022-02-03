package com.brihaspathee.zeus.exception;

import com.brihaspathee.zeus.web.response.ApiException;
import com.brihaspathee.zeus.web.response.ApiExceptionList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 20, January 2022
 * Time: 9:40 AM
 * Project: Zeus
 * Package Name: com.zeus.exception
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@ControllerAdvice
public class ApiErrorHandler {

    @ExceptionHandler(TradingPartnerNotFoundException.class)
    public ResponseEntity<ApiExceptionList> handleTPNotFoundException(TradingPartnerNotFoundException exception){
        log.info("Inside the no trading partner not found exception handler");
        ApiExceptionList exceptionList = getApiExceptionList(exception, "110001");
        return new ResponseEntity<>(exceptionList, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MultipleTradingPartnerFoundException.class)
    public ResponseEntity<ApiExceptionList> handleMultipleTPFoundException(MultipleTradingPartnerFoundException exception){
        log.info("Inside the multiple multiple trading partner matching exception handler");
        ApiExceptionList exceptionList = getApiExceptionList(exception, "110002");
        return new ResponseEntity<>(exceptionList, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ApiExceptionList> handleBindViolations(BindException exception){
        log.info("Inside the bind exception handler...");
        List<ApiException> errors = new ArrayList<>(exception.getAllErrors().size());
        exception.getAllErrors().forEach(error -> {
            ApiException apiException = ApiException.builder()
                    .exceptionCode(error.getCode())
                    .exceptionMessage(error.getDefaultMessage())
                    .build();
            errors.add(apiException);
        });
        ApiExceptionList exceptionList = ApiExceptionList.builder().exceptions(errors).build();
        return ResponseEntity.badRequest().body(exceptionList);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiExceptionList> handleConstraintViolations(ConstraintViolationException exception){
        log.info("Inside the exception handler");
        List<ApiException> errors = new ArrayList<>(exception.getConstraintViolations().size());
        exception.getConstraintViolations().forEach(constraintViolation -> {
            ApiException apiException = ApiException.builder()
                    .exceptionCode(constraintViolation.getPropertyPath().toString())
                    .exceptionMessage(constraintViolation.getMessage())
                    .build();
            errors.add(apiException);
        });
        ApiExceptionList exceptionList = ApiExceptionList.builder().exceptions(errors).build();
        return ResponseEntity.badRequest().body(exceptionList);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiExceptionList> handleDataIntegrityViolations(DataIntegrityViolationException exception){
        log.info("Inside the data integrity exception handler");
        ApiExceptionList exceptionList = getApiExceptionList(exception, "110003");
        return new ResponseEntity<>(exceptionList, HttpStatus.BAD_REQUEST);
    }


    private ApiExceptionList getApiExceptionList(Exception exception, String errorCode) {
        List<ApiException> errors = new ArrayList<>();
        ApiException apiException = ApiException.builder()
                .exceptionCode(errorCode)
                .exceptionMessage(exception.getMessage())
                .build();
        errors.add(apiException);
        ApiExceptionList exceptionList = ApiExceptionList.builder().exceptions(errors).build();
        return exceptionList;
    }
}