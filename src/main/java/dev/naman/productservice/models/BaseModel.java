package dev.naman.productservice.models;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.id.factory.spi.GenerationTypeStrategy;

import java.util.UUID;

@MappedSuperclass
public class BaseModel {

    @Id
    @GeneratedValue(generator = "uuidGenerator")
    @GenericGenerator(name = "uuidGenerator", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "binary(16)", nullable = false, updatable = false)
    UUID uuid;
}
