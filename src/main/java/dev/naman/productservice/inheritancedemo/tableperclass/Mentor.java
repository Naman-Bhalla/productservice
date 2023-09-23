package dev.daliya.productService.inheritancedemo.tableperclass;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity(name = "TPC_Mentor")
public class Mentor extends User {
    private double averageMentorRating;
}
