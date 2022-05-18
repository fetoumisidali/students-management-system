package com.example.students.management.config;

import com.example.students.management.custom_exceptions.LectureEntityException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Sidali Fetoumi
 * @date 5/11/2022
 * Enjoy Coding :)
 */
@ControllerAdvice
public class EntityExceptionHandler {

    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<Object> entityNotFoundExceptionHandler(EntityNotFoundException entityNotFoundException){
        Map<String,String> error = new HashMap<>();
        error.put("status", HttpStatus.NOT_FOUND.toString());
        error.put("message",entityNotFoundException.getMessage());
        return new ResponseEntity<Object>(error,HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler({LectureEntityException.class})
    public ResponseEntity<Object> lectureEntityExceptionHandler(LectureEntityException lectureEntityException){
        Map<String,String> error = new HashMap<>();
        error.put("status", HttpStatus.BAD_REQUEST.toString());
        error.put("message",lectureEntityException.getMessage());
        return new ResponseEntity<Object>(error,HttpStatus.BAD_REQUEST);
    }
}
