package dev.naman.productservice.services;

import dev.pranay.productservice.dtos.GenericProductDtoDB;
import dev.pranay.productservice.exception.NotFoundException;

import java.util.List;
import java.util.UUID;

public interface ProductServiceDB {
    GenericProductDtoDB createProduct(GenericProductDtoDB product);

    GenericProductDtoDB getProductById(UUID id)throws NotFoundException;

    List<GenericProductDtoDB> getAllProducts();

    GenericProductDtoDB deleteProductById(UUID id)throws NotFoundException;;

    GenericProductDtoDB updateProductById(GenericProductDtoDB genericProductDto,UUID id);

}
