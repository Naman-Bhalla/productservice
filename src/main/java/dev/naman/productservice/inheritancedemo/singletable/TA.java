package dev.daliya.productService.inheritancedemo.singletable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity(name = "ST_TA")
@DiscriminatorValue(value = "3")
public class TA extends User {
    private double averageRating;
}
