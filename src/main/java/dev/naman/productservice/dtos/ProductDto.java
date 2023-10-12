package dev.naman.productservice.dtos;

import dev.naman.productservice.models.Category;
import dev.naman.productservice.models.Price;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private String title;

    private String description;

    private String image;

    private Price price;


    //            P : C
    // => L to R: 1 : 1
    // => R to L: m : 1
    // => Ans:    m : 1


}