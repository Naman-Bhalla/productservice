package dev.naman.productservice.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ControllerAdvices {

//    @ExceptionHandler(NotFoundException.class)
//    private ResponseEntity<ExceptionDto> handleNotfoundException(NotFoundException notFoundException){
//        return  new ResponseEntity<>
//                (new ExceptionDto(HttpStatus.NOT_FOUND, notFoundException.getMessage()),
//                        HttpStatus.NOT_FOUND);
//    }
}
