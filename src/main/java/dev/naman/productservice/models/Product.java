package dev.naman.productservice.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.engine.internal.Cascade;

import java.util.UUID;

@Getter
@Setter
@Entity //Associates Product with DB
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseModel {
    private String title;
    private String description;
    private String image;
    //@Column(name = "category")
    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinTable(name = "category_id")
    private Category category;
    private double price;
    private String currency;

//    @OneToOne(cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
//    //This will cause the price to persist even if we don't write code for it
//    private Price price;
//    //private double price;
}
