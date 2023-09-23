package dev.daliya.productService.inheritancedemo.tableperclass;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity(name = "TPC_TA")
public class TA extends User {
    private double averageRating;
}
