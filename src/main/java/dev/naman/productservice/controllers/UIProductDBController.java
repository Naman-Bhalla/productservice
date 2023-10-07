package dev.naman.productservice.controllers;

import dev.naman.productservice.dtos.GenericProductDto;
import dev.naman.productservice.exceptions.NotFoundException;
import dev.naman.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/productservice")
public class UIProductDBController {

    private final ProductService productService;

    @Autowired
    public UIProductDBController(@Qualifier("selfProductServiceImpl") ProductService productService) {
        this.productService = productService;
    }

    // Get product by id
    // Create product
    // Update product
    // Delete product


    @GetMapping("/index")
    public String listRecords() {
//        model.addAttribute("records", recordService.getAllRecords());
        return "index";
    }

    @GetMapping("/get-all-products")
    public String listProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("productCount", productService.getAllProducts().size());
        return "get-all-products";
    }

//    @GetMapping("/productservice/get-single-product")
//    public String showForm(Model model) {
//
//        model.addAttribute("genericDto", new GenericProductDto());
//        return "get-single-product";
//    }
//
//    @PostMapping("/productservice/get-single-product")
//    public String fetchProduct(@ModelAttribute GenericProductDto genericProductDto, Model model) throws NotFoundException {
//
//        UUID uuid = UUID.fromString(String.valueOf(genericProductDto.getUuid()));
//        model.addAttribute("genericDto", productService.getProductById(uuid));
//        return "get-single-product";
//    }

    @GetMapping("/get-single-product")
    public String showForm(Model model) {
        model.addAttribute("genericDto", new GenericProductDto());
        return "get-single-product";
    }

    @PostMapping("/get-single-product")
    public String fetchProduct(@ModelAttribute GenericProductDto genericProductDto, Model model) {
        try {
            UUID uuid = UUID.fromString(genericProductDto.getUuid().toString());
            model.addAttribute("genericDto", productService.getProductById(uuid));
        } catch (NotFoundException e) {
            // Handle NotFoundException (Product not found)
            model.addAttribute("errorMessage", e.getMessage());
        }
        return "get-single-product";
    }

    @GetMapping("/create-a-product")
    public String showCreateForm(Model model) {

        model.addAttribute("genericProductDto", new GenericProductDto());
        return "create-a-product";
    }

    @PostMapping("/create-a-product")
    public String createProduct(@ModelAttribute GenericProductDto genericProductDto, Model model) {

        productService.createProduct(genericProductDto);
        return "redirect:/productservice/get-all-products";
    }

    @GetMapping("/update-a-product")
    public String showUpdateForm(Model model) {
        model.addAttribute("genericProductDto", new GenericProductDto());
        return "update-a-product";
    }

    @PostMapping("/update-a-product")
    public String showUpdateProductForm(@ModelAttribute GenericProductDto genericProductDto, Model model) {
        try {
            UUID uuid = genericProductDto.getUuid();
            model.addAttribute("genericProductDto", productService.getProductById(uuid));
        } catch (NotFoundException e) {
            model.addAttribute("error", e.getMessage());
        }
        return "update-a-product";
    }

    @PostMapping("/update-product")
    public String updateProduct(@ModelAttribute GenericProductDto genericProductDto, Model model) {
        try {
            productService.updateProduct(genericProductDto.getUuid(), genericProductDto);
            model.addAttribute("message", "Product updated successfully");
        } catch (NotFoundException e) {
            model.addAttribute("error", e.getMessage());
        }
        return "redirect:/productservice/get-all-products";
    }

    @GetMapping("/delete-a-product")
    public String showDeleteForm(Model model) {

        model.addAttribute("genericProductDto", new GenericProductDto());
        return "delete-a-product";
    }

    @PostMapping("/delete-a-product")
    public String fetchDeleteProduct(@ModelAttribute GenericProductDto genericProductDto, Model model) throws NotFoundException {

        model.addAttribute("genericProductDto", productService.getProductById(genericProductDto.getUuid()));
        return "delete-a-product";
    }

    @PostMapping("/delete-product")
    public String deleteProduct(@ModelAttribute GenericProductDto genericProductDto, Model model) throws NotFoundException {
        try {
            productService.deleteProduct(genericProductDto.getUuid());
            model.addAttribute("message", "Product updated successfully");
        } catch (NotFoundException e) {
            model.addAttribute("error", e.getMessage());
        }
        return "redirect:/productservice/get-all-products";
    }




}
