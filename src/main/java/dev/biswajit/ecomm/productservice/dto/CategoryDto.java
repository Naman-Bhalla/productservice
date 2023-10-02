package dev.biswajit.ecomm.productservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.UUID;

@Builder
public class CategoryDto {
    @JsonProperty(value = "id")
    private UUID categoryId;
    @JsonProperty(value = "title")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String title;
}
