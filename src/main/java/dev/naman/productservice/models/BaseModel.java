package dev.naman.productservice.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

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
