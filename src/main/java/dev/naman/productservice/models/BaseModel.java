package dev.naman.productService.models;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Data
@MappedSuperclass
public class BaseModel {

    @Id
    @GeneratedValue(generator = "UUIdGenerator")
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "binary(16)", nullable = false, updatable = false)
    private UUID id;
}
