package dev.biswajit.ecomm.productservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import reactor.core.publisher.Mono;

import static dev.biswajit.ecomm.productservice.exception.ErrorCode.INTERNAL_SERVER_ERROR;
import static dev.biswajit.ecomm.productservice.exception.ErrorCode.PRODUCT_NOT_FOUND;

@ControllerAdvice
public class ProductControllerAdvice {
    @ExceptionHandler(value = ProductNotFoundException.class)
    private ResponseEntity<Mono<ErrorDto>> productNotFoundException(ProductNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Mono.just(new ErrorDto(PRODUCT_NOT_FOUND, exception.getMessage())));
    }

    @ExceptionHandler
    private ResponseEntity<Mono<ErrorDto>> handleDefaultException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Mono.just(new ErrorDto(INTERNAL_SERVER_ERROR, e.getMessage())));
    }


}
