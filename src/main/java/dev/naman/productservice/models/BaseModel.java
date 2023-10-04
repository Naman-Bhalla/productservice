package dev.naman.productservice.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.id.factory.spi.GenerationTypeStrategy;

import java.util.UUID;

@MappedSuperclass
@Getter
@Setter
public class BaseModel {
    @Id
    @GeneratedValue(generator = "id_increment")
  @GenericGenerator(name = "id_increment", strategy  = "increment")
    //@Column(name = "id", columnDefinition = "binary(16)", nullable = false, updatable = false)
    private long id;
}
