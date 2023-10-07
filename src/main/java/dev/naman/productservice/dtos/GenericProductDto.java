package dev.naman.productservice.dtos;

import dev.naman.productservice.models.Category;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.Id;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GenericProductDto {

    //    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Getter
    @Id
    @GeneratedValue(generator = "uuidGenerator")
    @GenericGenerator(name = "uuidGenerator", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "binary(16)", nullable = false, updatable = false)
    private UUID uuid;

//        private Long id;    // for FakeStore API
    private String title;
    private String description;
    private String image;
    private double price;
    private String category;

    //    @Getter
    @Getter
    private String currency;

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return this.uuid;
    }
}
