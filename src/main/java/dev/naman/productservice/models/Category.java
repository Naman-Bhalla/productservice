package dev.naman.productservice.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category extends BaseModel {
    @Column
    private String name;

    @OneToMany(mappedBy = "category")
    @Fetch(FetchMode.SELECT)
    private List<Product> products = new ArrayList<>();

    // this is the same relation being mapped by category attribute in the other (Product) class
}
// class Group {
//   m:m
//   List<User> members;
//   m:m
//   List<User> admins;
//
//   1----> 1
//   m<---- 1
//   m   :  1
//   User creator;
// }