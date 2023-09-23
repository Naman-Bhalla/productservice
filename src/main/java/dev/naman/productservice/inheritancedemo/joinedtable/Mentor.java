package dev.daliya.productService.inheritancedemo.joinedtable;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity(name = "JT_Mentor")
public class Mentor extends User {
    private double averageMentorRating;
}
