package dev.naman.productservice.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity(name = "orders")
@Getter
@Setter
public class Order extends BaseModel {

    @ManyToMany
    @JoinTable(
            name = "product_orders",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )   // this means that the category field in the Product class is the owner of the relationship
    private List<Product> product;
    // we use the @JoinTable annotation to specify the join table that will be used to join the two entities.(Mapping table because of many to many relationship)
//    @ManyToMany()
//    @JoinTable(
//            name = "product_orders",
//            joinColumns = @JoinColumn(name = "order_id"),
//            inverseJoinColumns = @JoinColumn(name = "product_id")
//    )
//    private List<Product> product;
}
