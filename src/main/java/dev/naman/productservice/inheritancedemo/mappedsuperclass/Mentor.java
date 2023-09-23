package dev.daliya.productService.inheritancedemo.mappedsuperclass;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity(name = "MS_Mentor")
public class Mentor extends User {
    private double averageMentorRating;
}
