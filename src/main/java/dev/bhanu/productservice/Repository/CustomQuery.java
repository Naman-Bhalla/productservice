package dev.bhanu.productservice.Repository;

public interface CustomQuery {
    String FIND_ALL_PRODUCT = "SELECT p FROM Product p JOIN FETCH p.category";
    String FIND_ALL_CATEGORY = "select * from category";


}
