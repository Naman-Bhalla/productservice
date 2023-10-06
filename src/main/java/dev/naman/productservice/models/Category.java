package dev.naman.productservice.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Category extends BaseModel{
    @Column
    private String name;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "category")
    private List<Product> products;


}
