package dev.naman.productservice.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseModel {

    private String title;

    private String description;

    private String image;
    // P : C
    // => L to R: 1 : 1
    // => R to L: m : 1
    // => Ans: m : 1
    @ManyToOne(cascade = { CascadeType.PERSIST })
    @JoinColumn(name = "category")
    private Category category;

    @OneToOne(cascade = { CascadeType.PERSIST, CascadeType.REMOVE }, fetch = FetchType.EAGER)
    // @Fetch(FetchMode.JOIN)
    private Price price;
    // private double price;
}
