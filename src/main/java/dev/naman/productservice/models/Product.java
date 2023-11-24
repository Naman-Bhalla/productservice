package dev.naman.productservice.models;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.elasticsearch.annotations.Document;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "productservice")
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

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.LAZY)
//    @Fetch(FetchMode.JOIN)
    private Price price;
    private int inventoryCount;
//    private double price;
}

// select * from products where title startsBy "hello"
// a datastore optimized for search queries
// -> elasticsearch