package dev.naman.productservice.controllers;

import dev.naman.productservice.dtos.GenericProductDto;
import dev.naman.productservice.exceptions.NotFoundException;
import dev.naman.productservice.services.FakeStoreProductService;
import dev.naman.productservice.services.ProductService;
import dev.naman.productservice.thirdpartyclients.productsservice.fakestore.FakeStoryProductServiceClient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ProductControllerTest {

    @Autowired
    private FakeStoryProductServiceClient fakeStoryProductServiceClient;

    @Autowired
    private ProductController productController;

    @MockBean
    private ProductService productService;

    @Autowired
    private FakeStoreProductService fakeStoreProductService;

//    @Test
//    void returnsNullWhenProductDoesntExist() throws NotFoundException {
//        when(
//                productService.getProductById(any(Long.class))
//        ).thenReturn(null);
//
//
//        GenericProductDto genericProductDto = productController.getProductById(121L);
////        given
//
//
//        assertNull(genericProductDto);
//    }

    @Test
    void throwsExceptionWhenProductDoesntExist() throws NotFoundException {
        when(
                productService.getProductById(any(Long.class))
        )
                .thenReturn(null);

        assertThrows(NotFoundException.class, () -> productController.getProductById(123L));
    }

    @Test
    void returnsSameProductAsServiceWhenProductExists() throws NotFoundException {
        GenericProductDto genericProductDto = new GenericProductDto();
        when(
                productService.getProductById(any(Long.class))
        )
                .thenReturn(genericProductDto);

        assertEquals(genericProductDto.getPrice(), productController.getProductById(123L).getPrice());

//        assertThrows(NotFoundException.class, () -> productController.getProductById(123L));
    }

    @Test
    void shouldReturnTitleNamanWithProductID1() throws NotFoundException {
        GenericProductDto genericProductDto = new GenericProductDto();
        genericProductDto.setTitle("Naman");
        when(
                productService.getProductById(1L)
        ).thenReturn(
                genericProductDto
        );


        GenericProductDto genericProductDto1 = productController.getProductById(1L);
        assertEquals("Naman", genericProductDto1.getTitle());
    }

    @Test
    @DisplayName("1 + 1 equals 2")
    void onePlusOneEqualsTrue() throws NotFoundException {
//        System.out.println("It is true");
//        assertEquals(11, 1 + 1, "one plus is not coming to be 11");

//        assert

//        assertNull(fakeStoryProductServiceClient.getProductById(101L));

//        Exception e;
//
//        try {
//            fakeStoryProductServiceClient.getProductById(101L);
//        } catch (Exception ex) {
//            e = ex;
//        }
//
//        assertNotNull(e);
//        assertEquals(NotFoundException.class, e.getClass());

//        assertEquals(null, fakeStoryProductServiceClient.getProductById(101L));
//        assertThrows(NotFoundException.class, () -> fakeStoryProductServiceClient.getProductById(101L));
//
//        assertEquals(true, 1 + 1 == 2);
        assertTrue(returnSomething());
    }

    boolean returnSomething() {
        Random random = new Random();
        return random.nextInt() % 2 == 0;
    }

    @Test
    void additionShouldBeCorrect() {
        assertTrue(-1 + -1 == -2, "adding 2 negatives is not correct");

        assertTrue(-1 + 0 == -1, "adding a negative and a zero is giving wrong answer");

        assertTrue(-1 + 1 == 0);

        assert 1 + 0 == 1;

        assert 1 + 1 == 2;
    }
}

// Assertion Framework
// -> allows you to make assertions
// -> allows you to make checks