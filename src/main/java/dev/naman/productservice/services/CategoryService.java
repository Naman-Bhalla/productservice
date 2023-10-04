package dev.naman.productservice.services;

import dev.naman.productservice.models.Category;

import java.util.List;

public interface CategoryService {
    Category getCategory(long id);
    List<String> getProductTitles(List<Long> categoryIDs);
}
