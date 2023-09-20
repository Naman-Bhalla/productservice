package dev.naman.productservice.models;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseModel {

    private String title;

    private String description;

    private String image;
    //            P : C
    // => L to R: 1 : 1
    // => R to L: m : 1
    // => Ans:    m : 1
    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "category")
    private Category category;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Price price;
//    private double price;
}
