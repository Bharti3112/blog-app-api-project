package com.blogapplication.api.exceptions;


import com.blogapplication.api.payloads.APIREsponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<APIREsponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex){
        String message = ex.getMessage();
       APIREsponse apirEsponse = new APIREsponse(message,false);
       return new ResponseEntity<APIREsponse>(apirEsponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> methodArgumentNotValidException(MethodArgumentNotValidException ex){
        Map<String,String> errorResp = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError)error).getField();
            String message = error.getDefaultMessage();
            errorResp.put(fieldName,message);
        });
        return new ResponseEntity<Map<String,String>>(errorResp,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(APIExceptions.class)
    public ResponseEntity<APIREsponse> handleApiException(APIExceptions ex) {
        String message = ex.getMessage();
        APIREsponse apiResponse = new APIREsponse(message, true);
        return new ResponseEntity<APIREsponse>(apiResponse, HttpStatus.BAD_REQUEST);
    }
}
