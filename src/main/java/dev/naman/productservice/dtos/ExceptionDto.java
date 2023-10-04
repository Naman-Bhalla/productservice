package dev.naman.productservice.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ExceptionDto {
    private HttpStatus errorCode;
    private String message;

    public ExceptionDto(HttpStatus errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }
}
