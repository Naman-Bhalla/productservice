package dev.daliya.productService.inheritancedemo.singletable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity(name = "ST_Mentor")
@DiscriminatorValue(value = "1")
public class Mentor extends User {
    private double averageMentorRating;
}
