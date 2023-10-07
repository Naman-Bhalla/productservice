package dev.naman.productservice.exceptions;

import dev.naman.productservice.dtos.ExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerAdvices {
//    @ExceptionHandler(NotFoundException.class)
//    private ResponseEntity<ExceptionDto> handleNotFoundException(
//            NotFoundException notFoundException
//    ) {
//
//        return new ResponseEntity(
//                new ExceptionDto(HttpStatus.NOT_FOUND, notFoundException.getMessage()),
//                HttpStatus.NOT_FOUND
//        );
//    }
//
//    @ExceptionHandler(ArrayIndexOutOfBoundsException.class)
//    private ResponseEntity<ExceptionDto> handleArrayIndexOutOfBound(
//            ArrayIndexOutOfBoundsException notFoundException
//    ) {
//
//        return new ResponseEntity(
//                new ExceptionDto(HttpStatus.NOT_FOUND, notFoundException.getMessage()),
//                HttpStatus.NOT_FOUND
//        );
//    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "error";
    }


}
