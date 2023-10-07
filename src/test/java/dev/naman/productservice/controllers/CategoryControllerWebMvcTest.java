package dev.naman.productservice.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.naman.productservice.dtos.CategoryDto;
import dev.naman.productservice.dtos.GenericProductDto;
import dev.naman.productservice.services.CategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CategoryController.class)
public class CategoryControllerWebMvcTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService categoryService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllCategoriesReturnsEmptyListWhenNoCategories() throws Exception {
        when(categoryService.getAllCategories())
                .thenReturn(new ArrayList<>());

        mockMvc.perform(get("/categories"))
                .andExpect(status().is(404))
                .andExpect(content().string("[]"));
    }

    @Test
    void getAllCategoriesReturnsCatgoryListWhenExists() throws Exception {
        List<CategoryDto> categoryDtos = new ArrayList<>();
        categoryDtos.add(new CategoryDto());
        categoryDtos.add(new CategoryDto());
        categoryDtos.add(new CategoryDto());
        categoryDtos.add(new CategoryDto());
        when(categoryService.getAllCategories())
                .thenReturn(categoryDtos);


        mockMvc.perform(get("/categories"))
           .andExpect(
                status().is(200)
            ).andExpect(
                content().string(objectMapper.writeValueAsString(categoryDtos))
            );
    }

    @Test
    void getProductsByCategoryReturnsEmptyListWhenNoProductExistsInCategory() throws Exception {
        when(categoryService.getProductsByACategory(any()))
                .thenReturn(new ArrayList<>());

        mockMvc.perform(get("/categories/name/Mobiles"))
                .andExpect(status().is(404))
               .andExpect(content().string("[]"));
    }

    @Test
    void getProductsByCategoryReturnsProductListWhenProductExistsInCategory() throws Exception {
        List<GenericProductDto> productDtos = new ArrayList<>();
        productDtos.add(new GenericProductDto());
        productDtos.add(new GenericProductDto());
        productDtos.add(new GenericProductDto());
        productDtos.add(new GenericProductDto());
        when(categoryService.getProductsByACategory(any()))
                .thenReturn(productDtos);

        mockMvc.perform(get("/categories/name/Mobiles"))
                .andExpect(
                        status().is(200)
                ).andExpect(
                        content().string(objectMapper.writeValueAsString(productDtos))
                );
    }
}
