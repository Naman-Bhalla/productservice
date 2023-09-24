package dev.naman.productservice.models;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category extends BaseModel {
    @Column
    private String name;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    @Fetch(FetchMode.SELECT)
    private List<Product> products = new ArrayList<>();

    // this is the same relation being mapped by category attribute in the other
    // (Product) class
}
// class Group {
// m:m
// List<User> members;
// m:m
// List<User> admins;
//
// 1----> 1
// m<---- 1
// m : 1
// User creator;
// }