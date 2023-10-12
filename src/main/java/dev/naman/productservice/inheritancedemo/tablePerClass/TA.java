package dev.naman.productservice.inheritancedemo.tablePerClass;

import dev.naman.productservice.inheritancedemo.tablePerClass.User;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "tpc_ta")
public class TA extends User {
    private double averageRating;
}