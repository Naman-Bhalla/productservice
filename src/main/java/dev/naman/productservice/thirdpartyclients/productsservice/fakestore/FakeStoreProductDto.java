package dev.naman.productservice.thirdpartyclients.productsservice.fakestore;

import dev.naman.productservice.models.Category;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FakeStoreProductDto {

    //output
    //    {
    //        id:1,
    //        title:'...',
    //        price:'...',
    //        category:'...',
    //        description:'...',
    //        image:'...'
    //    }

    private Long id;
    private String title;
    private double price;
    private String category;
    private String description;
    private String image;
}

// Break till 10:35