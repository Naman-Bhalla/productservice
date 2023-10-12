package dev.naman.productservice.inheritancedemo.tablePerClass;

import dev.naman.productservice.inheritancedemo.tablePerClass.User;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "tpc_mentor")
public class Mentor extends User {
    private double averageRating;
}