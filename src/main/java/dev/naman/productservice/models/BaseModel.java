package dev.naman.productservice.models;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.id.factory.spi.GenerationTypeStrategy;

import java.util.UUID;

@MappedSuperclass
public class BaseModel {
    @Id
    @GeneratedValue(generator = "naman")
    @GenericGenerator(name = "naman", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "uuid", nullable = false, updatable = false)
    private UUID uuid;
}
