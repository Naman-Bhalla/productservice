package dev.daliya.productService.inheritancedemo.joinedtable;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity(name = "JT_TA")
public class TA extends User {
    private double averageRating;
}
