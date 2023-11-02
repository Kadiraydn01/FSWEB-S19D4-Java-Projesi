package com.example.employee.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler
    public ResponseEntity<EmployeeErrorResponse> handleException(EmployeeException exception){
        EmployeeErrorResponse response = new EmployeeErrorResponse(exception.getStatus().value(), exception.getMessage());
        return new ResponseEntity<>(response,exception.getStatus());
    }

   @ExceptionHandler
   public ResponseEntity<EmployeeErrorResponse> handleException(Exception e){
        EmployeeErrorResponse response = new EmployeeErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
   }





}
