package com.ecommerce.APIecommerce.exceptions;

public class ProductNotExistsException extends IllegalArgumentException{
    public ProductNotExistsException(String msg) {
        super(msg);
    }
}
