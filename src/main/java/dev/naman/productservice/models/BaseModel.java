package dev.naman.productservice.models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.id.factory.spi.GenerationTypeStrategy;

import java.util.UUID;

@Data
@MappedSuperclass
public class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
   // @GenericGenerator(name = "naman", strategy = "uuid2")
    //@Column(name = "id", columnDefinition = "binary(16)", nullable = false, updatable = false)
    private Long id;
}
