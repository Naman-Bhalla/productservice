package dev.naman.productservice.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category extends BaseModel {
    @Column
    private String name;

    @OneToMany(mappedBy = "category")
    @Fetch(FetchMode.SELECT)
    private List<Product> products;
}