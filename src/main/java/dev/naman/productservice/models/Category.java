package dev.naman.productservice.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity (name = "category")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category extends BaseModel {
    private String name;

//    @OneToMany
//    private List<Product> products;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    // this means that the category field in the Product class is the owner of the relationship
    @OneToMany(mappedBy = "category")
    @Fetch(FetchMode.SUBSELECT)
    private List<Product> products;


    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return this.uuid;
    }
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