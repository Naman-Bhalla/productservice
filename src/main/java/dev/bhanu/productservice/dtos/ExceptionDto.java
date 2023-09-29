package dev.bhanu.productservice.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;


@Getter
@Setter
public class ExceptionDto {
    private String message;
    private HttpStatus status;

    public ExceptionDto(String message, HttpStatus status){
        this.message = message;
        this.status = status;
    }
}
