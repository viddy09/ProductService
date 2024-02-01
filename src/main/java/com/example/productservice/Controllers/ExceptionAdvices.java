package com.example.productservice_proxy_assignment.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionAdvices {
    @ExceptionHandler({Exception.class})
    public ResponseEntity<String> handleException(){
        return new ResponseEntity<>("Something went wrong!!!", HttpStatus.OK);
    }
}
