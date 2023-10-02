package dev.biswajit.ecomm.productservice.thirdparty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ThirdPartyProductDto {
    private Long id;
    private String title;
    private String price;
    private String category;
    private String description;
    private String imageUrl;
}
