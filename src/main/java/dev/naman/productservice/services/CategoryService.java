package dev.naman.productservice.services;

import dev.naman.productservice.models.Category;

public interface CategoryService {
    Category getCategory(String uuid);
}
