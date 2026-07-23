package com.grocerystore.backend.service;

import com.grocerystore.backend.dto.ProductRequest;
import com.grocerystore.backend.dto.ProductResponse;
import com.grocerystore.backend.exception.ProductNotFoundException;
import com.grocerystore.backend.model.Product;
import com.grocerystore.backend.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductResponse> responses = new ArrayList<>();

        for (Product product : products) {
            responses.add(toResponse(product));
        }

        return responses;
    }

    public ProductResponse getProductById(Long id) {
        Product product = findProductOrThrow(id);
        return toResponse(product);
    }

    public ProductResponse createProduct(ProductRequest request) {
        Product product = new Product();
        applyRequest(product, request);

        Product saved = productRepository.save(product);
        return toResponse(saved);
    }

    public ProductResponse updateProduct(Long id, ProductRequest request) {
        Product existing = findProductOrThrow(id);
        applyRequest(existing, request);

        Product saved = productRepository.save(existing);
        return toResponse(saved);
    }

    public void deleteProduct(Long id) {
        Product product = findProductOrThrow(id);
        productRepository.delete(product);
    }

    // Hjelpemetoder

    private Product findProductOrThrow(Long id) {
        Optional<Product> result = productRepository.findById(id);
        if (result.isEmpty()) {
            throw new ProductNotFoundException(id);
        }
        return result.get();
    }

    private void applyRequest(Product product, ProductRequest request) {
        product.setName(request.name());
        product.setDescription(request.description());
        product.setPrice(request.price());
        product.setStockQuantity(request.stockQuantity());
        product.setCategory(request.category());
        product.setImageUrl(request.imageUrl());
    }

    private ProductResponse toResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStockQuantity(),
                product.getCategory(),
                product.getImageUrl()
        );
    }
}