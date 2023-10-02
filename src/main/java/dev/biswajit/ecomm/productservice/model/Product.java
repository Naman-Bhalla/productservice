package dev.biswajit.ecomm.productservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "PRODUCTS")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToOne(cascade = {CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE},
            fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "price_id", referencedColumnName = "id")
    @BatchSize(size = 10)
    @Fetch(FetchMode.SELECT)
    private Price price;

    private String image;

    public Product(String title, Category category, Price price, String imageUrl) {
        this.name = title;
        this.category = category;
        this.price = price;
        this.image = imageUrl;
    }
}
