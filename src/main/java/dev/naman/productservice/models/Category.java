package dev.naman.productService.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.*;
import org.hibernate.annotations.Fetch;

import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Category extends BaseModel {
    private String name;

    private String description;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "category")
    @Fetch(org.hibernate.annotations.FetchMode.SUBSELECT)
    private List<Product> products;
}
