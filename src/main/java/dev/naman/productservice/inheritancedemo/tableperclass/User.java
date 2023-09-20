package dev.naman.productservice.inheritancedemo.tableperclass;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@ToString
@Entity(name = "tpc_user")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class User {
    @Id
//    @GeneratedValue(strategy = GenerationType.TABLE, generator="course")
    private UUID id;
    private String name;
    private String email;
}
