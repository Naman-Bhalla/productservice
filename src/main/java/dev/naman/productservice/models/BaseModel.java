package dev.naman.productservice.models;

import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class BaseModel {
    @Id
    // @GeneratedValue(generator = "naman")
    // @GenericGenerator(name = "naman", strategy = "uuid2")
    // @Column(name = "id", columnDefinition = "", nullable = false, updatable =
    // false)
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "BINARY(16)")
    private UUID id;
}
