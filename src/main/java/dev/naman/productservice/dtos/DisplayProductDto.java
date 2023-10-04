package dev.naman.productservice.dtos;


import dev.naman.productservice.models.Price;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DisplayProductDto {
//
//    //    @Id
////    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Id
//    @GeneratedValue(generator = "uuidGenerator")
//    @GenericGenerator(name = "uuidGenerator", strategy = "uuid2")
//    @Column(name = "id", columnDefinition = "binary(16)", nullable = false, updatable = false)
//    private UUID uuid;

    //    private Long id;
    private String title;
    private String description;
    private String image;
    private Price price;
    private String category;


    public void setCategory(String category) {
        this.category = category;
    }
    public String getCategory() {
        return category;
    }

}