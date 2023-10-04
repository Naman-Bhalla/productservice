package dev.naman.productservice.controllers;

import dev.naman.productservice.exceptions.NotFoundException;
import dev.naman.productservice.thirdpartyclients.productsservice.fakestore.FakeStoreProductServiceClient;
import dev.naman.productservice.thirdpartyclients.productsservice.fakestore.FakeStoreProductServiceClient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ProductControllerTest {

    @Autowired
    private FakeStoreProductServiceClient fakeStoreProductServiceClient;


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