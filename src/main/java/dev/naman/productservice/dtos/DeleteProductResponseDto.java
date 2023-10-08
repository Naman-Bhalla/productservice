package dev.naman.productservice.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.DeleteMapping;

@Getter
@Setter
public class DeleteProductResponseDto {
    private Long id;
    private String title;
    private String category;

    private String description;

    private String image;
    //            P : C
    // => L to R: 1 : 1
    // => R to L: m : 1
    // => Ans:    m : 1
    private double price;
}
