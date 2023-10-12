package dev.naman.productservice.services;

import dev.naman.productservice.dtos.GenericProductDto;
import dev.naman.productservice.exceptions.NotFoundException;
import dev.naman.productservice.models.Category;
import dev.naman.productservice.models.Price;
import dev.naman.productservice.models.Product;
import dev.naman.productservice.repositories.CategoryRepository;
import dev.naman.productservice.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.*;


//@Primary
@Service("selfProductServiceImpl")
public class SelfProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public SelfProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }


    // utility method to map Product to GenericProductDto
    private GenericProductDto copyProductDtoProperties(Product source) {
        GenericProductDto target = new GenericProductDto();
        target.setUuid(source.getUuid());
        target.setTitle(source.getTitle());
        target.setDescription(source.getDescription());
        target.setImage(source.getImage());
        target.setPrice(source.getPrice().getPrice());
        target.setCurrency(source.getCurrency());
        target.setCategory(source.getCategory().getName());
        return target;
    }

    // UTILITY METHOD to map Product to GenericProductDto
    private GenericProductDto mapProductToGenericProductDto(Product product) {
        GenericProductDto genericProductDto = new GenericProductDto();
        genericProductDto.setUuid(product.getUuid());
        genericProductDto.setTitle(product.getTitle());
        genericProductDto.setDescription(product.getDescription());
        genericProductDto.setImage(product.getImage());
        genericProductDto.setPrice(product.getPrice().getPrice());
        genericProductDto.setCurrency(product.getCurrency());
        genericProductDto.setCategory(product.getCategory().getName());
        return genericProductDto;
    }

    @Override
    public String getQualifierValue() {
        return "DB Product Service";
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto genericProductDto) {

        // This should be the JSON format for the request body
//        {
//                "uuid": "e4856e55-8908-4805-8ff9-7e1184374323",
//                "title": "Dummy Product 1",
//                "description": "Dummy Description 1",
//                "image": "https://fakestoreapi.com/img/71YXzeOuslL._AC_UY879_.jpg",
//                "price": 11.11,
//                "category": "Dummy category 1",
//                "currency": "Won"
//        }

        // find the category by name if exists
        Optional<Category> optionalCategory = categoryRepository.findByNameIgnoreCase(genericProductDto.getCategory());

        Category category;
        if (optionalCategory.isPresent()) {
            category = optionalCategory.get();
        } else {
            category = new Category();
            category.setName(genericProductDto.getCategory());
            categoryRepository.save(category);
        }
        Product product = new Product();
        product.setUuid(genericProductDto.getUuid());
        product.setTitle(genericProductDto.getTitle());
        product.setDescription(genericProductDto.getDescription());
        product.setImage(genericProductDto.getImage());

        Price price = new Price();
        price.setPrice(genericProductDto.getPrice());
        price.setCurrency(genericProductDto.getCurrency());
        product.setPrice(price);

        product.setCategory(category);

        Product savedProduct = productRepository.save(product);
        return mapProductToGenericProductDto(savedProduct);
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
        List<Product> allProducts = productRepository.findAll();

        if (allProducts.isEmpty()) {
            // Return an empty list instead of null, if no products are found in the database
            return Collections.emptyList();
        }
        List<GenericProductDto> productDtosList = new ArrayList<>();
        for (Product product : allProducts) {
            productDtosList.add(mapProductToGenericProductDto(product));
        }
        return productDtosList;
    }

    @Override
    public GenericProductDto getProductById(UUID uuid) throws NotFoundException {
        Product product = productRepository.findById(UUID.fromString(uuid.toString()))
                .orElseThrow(() -> new NotFoundException("Product with ID: " + uuid + " not found. Please try again."));

        return mapProductToGenericProductDto(product);
    }

    @Override
    public GenericProductDto updateProduct(UUID uuid, GenericProductDto genericProductDto) throws NotFoundException {

        // This should be the JSON format for the request body
//        {
//                "uuid": "e4856e55-8908-4805-8ff9-7e1184374323",
//                "title": "Dummy Product 1",
//                "description": "Dummy Description 1",
//                "image": "https://fakestoreapi.com/img/71YXzeOuslL._AC_UY879_.jpg",
//                "price": 11.11,
//                "category": "Dummy category 1",
//                "currency": "Won"
//        }

        Optional<Product> optionalProduct = productRepository.findById(UUID.fromString(String.valueOf(uuid)));
        if (optionalProduct.isEmpty())
            throw new NotFoundException("Product with ID : " + uuid + " NOT FOUND, Update Failed.");

        Product product = optionalProduct.get();

        // checking if uuid != product.getUuid() then throw exception
        if (!uuid.equals(product.getUuid()))
            throw new NotFoundException("Product ID : " + uuid + " does not match the Request Body ID : " + product.getUuid() + " in the database. Update Failed.");

        // get the category provided by user
//        String categoryValue = genericProductDto.getCategory();
        Optional<Category> optionalCategory = categoryRepository.findByNameIgnoreCase(genericProductDto.getCategory());

        Category category;
        if (optionalCategory.isPresent()) {
            category = optionalCategory.get();
        } else {
            category = new Category();
            category.setName(genericProductDto.getCategory());
            categoryRepository.save(category);
        }

        product.setUuid(uuid);
        product.setTitle(genericProductDto.getTitle());
        product.setDescription(genericProductDto.getDescription());
        product.setImage(genericProductDto.getImage());
        Price price = new Price();
        price.setPrice(genericProductDto.getPrice());
        price.setCurrency(genericProductDto.getCurrency());
        product.setPrice(price);

        category.setName(genericProductDto.getCategory());
        product.setCategory(category);

        Product savedProduct = productRepository.save(product);
        return copyProductDtoProperties(savedProduct);
    }

    @Override
    public GenericProductDto deleteProduct(UUID id) throws NotFoundException {

        Optional<Product> optionalProduct = productRepository.findById(UUID.fromString(String.valueOf(id)));
        if (optionalProduct.isEmpty())
            throw new NotFoundException("Product with ID : " + id + " NOT FOUND, Deletion Failed.");

        Product product = optionalProduct.get();

        GenericProductDto productDto = new GenericProductDto();
        productDto.setTitle(product.getTitle());
        productDto.setDescription(product.getDescription());
        productDto.setImage(product.getImage());
        productDto.setPrice(product.getPrice().getPrice());
        productDto.setCategory(product.getCategory().getName());

        productRepository.delete(product);
        return productDto;
    }

//    OLD CODE FOR REFERENCE

}
