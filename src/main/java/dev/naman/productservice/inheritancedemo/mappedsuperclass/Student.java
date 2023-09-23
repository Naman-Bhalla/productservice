package dev.daliya.productService.inheritancedemo.mappedsuperclass;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity(name = "MS_Student")
public class Student extends User {
    private double psp;
    private double attendance;
}
