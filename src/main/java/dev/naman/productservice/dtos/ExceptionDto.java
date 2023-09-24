package dev.naman.productService.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ExceptionDto {
    private HttpStatus httpStatus;
    private String message;

    public ExceptionDto(HttpStatus status, String message) {
        this.httpStatus = status;
        this.message = message;
    }
}
