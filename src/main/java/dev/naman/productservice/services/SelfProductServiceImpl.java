package dev.naman.productservice.services;

import dev.naman.productservice.dtos.GenericProductDto;
import dev.naman.productservice.models.Product;
import dev.naman.productservice.repositories.ProductRepository;
import dev.naman.productservice.security.JwtObject;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


//@Primary
@Service("selfProductServiceImpl")
public class SelfProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    public SelfProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public GenericProductDto getProductById(Long id) {
        System.out.println("In product service");

        // Product product = ProductRepository.getProductByID(id);
        //  if (product.getStatus().equals(PRIVATE)) {
        //      if (userIdTryingToAccess.equals(product.getCreatorId())) {
        //        return product;
        //      }
        //       return null;
        //  }
        //
        // return product;

        return new GenericProductDto();
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto product) {
        return null;
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();// get first 10 products or less (whatever is minimum)


//        PageRequest pageRequest = PageRequest.of(3, 10);
//        // get me the 2nd page
//        // where size of each page is 10
//
//        List<Product> products1 = productRepository.getAll(pageRequest);


        List<GenericProductDto> genericProductDtos = new ArrayList<>();

        for (Product product: products) {
            genericProductDtos.add(GenericProductDto.from(product));
        }

        return genericProductDtos;
    }

    @Override
    public GenericProductDto deleteProduct(Long id) {
        return null;
    }
}
