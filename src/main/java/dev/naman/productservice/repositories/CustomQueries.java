package dev.naman.productservice.repositories;

public interface CustomQueries {
    String GET_ALL_PRODUCT_BY_CATEGORY = "Select p.* from product p left join category c on p.category = c.id where c.name=:categoryName";
    String GET_ALL_PRODUCT_CATEGORY = "Select distinct c.name from product p left join category c on p.category = c.id";
    String FIND_ALL_BY_TITLE = "select * from product join product_orders " +
            "on product.id = product_orders.product_id where title = :naman";

    String FIND_ALL_PRODUCT = "SELECT DISTINCT p FROM Product p LEFT JOIN FETCH p.category LEFT JOIN FETCH p.price";
}
