package dev.biswajit.ecomm.productservice.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ErrorDto {
    private final ErrorCode code;
    private final String message;
}
