package dev.naman.productservice.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity //Associates Product with DB
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseModel {
    private String title;
    private String description;
    private String image;
    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinTable(name = "category_id")
    private Category category;
    private double price;
    private String currency;
}