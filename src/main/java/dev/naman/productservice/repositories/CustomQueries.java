package dev.naman.productservice.repositories;

import dev.naman.productservice.dtos.GenericProductDto;

import java.util.List;

public interface CustomQueries {
    String FIND_ALL_BY_TITLE = "select * from product join product_orders " +
            "on product.id = product_orders.product_id where title = :naman";
    List<GenericProductDto> findAllProducts();
}
