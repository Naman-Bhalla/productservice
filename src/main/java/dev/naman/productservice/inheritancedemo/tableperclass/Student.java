package dev.daliya.productService.inheritancedemo.tableperclass;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity(name = "TPC_Student")
public class Student extends User {
    private double psp;
    private double attendance;
}
