package dev.naman.productService.thirdPartyClients.productService.fakeStore;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class FakeStoreProductDto {
    private UUID id;
    private String title;
    private double price;
    private String category;
    private String description;
    private String image;
}
