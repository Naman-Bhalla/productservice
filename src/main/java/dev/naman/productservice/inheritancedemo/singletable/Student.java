package dev.daliya.productService.inheritancedemo.singletable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity(name = "ST_Student")
@DiscriminatorValue(value = "2")
public class Student extends User {
    private double psp;
    private double attendance;
}
