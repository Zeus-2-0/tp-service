package com.brihaspathee.zeus.exception;

import com.brihaspathee.zeus.web.response.ApiException;
import com.brihaspathee.zeus.web.response.ApiExceptionList;
import com.networknt.schema.ValidationMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.validation.ConstraintViolationException;
import java.util.*;

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

    @ExceptionHandler(ZeusApiValidationException.class)
    public ResponseEntity<ApiExceptionList> handleApiValidationExceptions(ZeusApiValidationException exception){
        log.info("Inside the no trading partner not found exception handler");
        Set<ValidationMessage> validationMessageSet = exception.getValidationMessages();
        if(validationMessageSet.size() > 0){

            List<ApiException> errors = new ArrayList<>();
            for (ValidationMessage validationMessage: validationMessageSet){
                ApiException apiException = ApiException.builder()
                        .exceptionMessage(validationMessage.toString())
                        .build();
                errors.add(apiException);
            }
            ApiExceptionList apiExceptionList = ApiExceptionList.builder().exceptions(errors).build();
            return new ResponseEntity<>(apiExceptionList, HttpStatus.BAD_REQUEST);
        }
        return null;
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiExceptionList> handleBadCredentialsException(BadCredentialsException exception){
        log.info("Inside the no trading partner not found exception handler");
        //ApiExceptionList exceptionList = getApiExceptionList(exception, "110001");
        //return new ResponseEntity<>(exceptionList, HttpStatus.NOT_FOUND);
        return null;
    }

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
