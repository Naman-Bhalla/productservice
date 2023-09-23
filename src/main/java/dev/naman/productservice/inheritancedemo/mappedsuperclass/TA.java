package dev.daliya.productService.inheritancedemo.mappedsuperclass;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity(name = "MS_TA")
public class TA extends User {
    private double averageRating;
}
