package dev.daliya.productService.inheritancedemo.joinedtable;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity(name = "JT_Student")
public class Student extends User {
    private double psp;
    private double attendance;
}
