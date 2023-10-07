package dev.naman.productservice.controllers;

import dev.naman.productservice.dtos.CategoryDto;
import dev.naman.productservice.dtos.GenericProductDto;
import dev.naman.productservice.exceptions.NotFoundException;
import dev.naman.productservice.models.Product;
import dev.naman.productservice.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/productservice")
public class UICategoryDBController {

    private CategoryService categoryService;

    @Autowired
    public UICategoryDBController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/get-all-categories")
    public String listCategories(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("categoryCount", categoryService.getAllCategories().size());
        return "get-all-categories";
    }

    @GetMapping("/get-in-category")
    public String showForm(Model model) {

        model.addAttribute("categoryDto", new CategoryDto());
        return "get-in-category";
    }

    @PostMapping("/get-in-category")
    public String fetchProducts(@ModelAttribute CategoryDto categoryDto, Model model) throws NotFoundException {

        String uuid = String.valueOf(categoryDto.getUuid());
        List<Product> products = categoryService.getAllProductsFromACategory(uuid).getProducts();
        model.addAttribute("productCountFromCategory", products.size()  );
        List<GenericProductDto> productDtos = new ArrayList<>();

        for (Product product: products) {
            GenericProductDto productDto = new GenericProductDto();
            productDto.setDescription(product.getDescription());
            productDto.setUuid(product.getUuid());
            productDto.setTitle(product.getTitle());
            productDto.setImage(product.getImage());
            productDto.setPrice(product.getPrice().getPrice());
            productDto.setCurrency(product.getCurrency());
            productDto.setCategory(product.getCategory().getName());
            productDtos.add(productDto);
        }
        model.addAttribute("genericProductDto", productDtos);
        return "get-in-category";
    }
}
