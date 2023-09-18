package dev.naman.productservice.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity(name = "orders")
@Getter
@Setter
public class Order extends BaseModel {

    @ManyToMany()
    @JoinTable(
            name = "product_orders",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> product;
}
