package com.grocerystore.backend.exception;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(Long id){
        super("Fant ikke produkt med id "+ id);
    }
}
