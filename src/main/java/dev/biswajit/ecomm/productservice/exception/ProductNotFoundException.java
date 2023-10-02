package dev.biswajit.ecomm.productservice.exception;

public class ProductNotFoundException extends Exception{
    private ProductNotFoundException(String message) {
        super(message);
    }

    public static ProductNotFoundException with(String message) {
        return new ProductNotFoundException(message);
    }

}
