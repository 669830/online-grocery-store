package com.grocerystore.backend.repository;

import com.grocerystore.backend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ProductRepository extends JpaRepository<Product, Long>{
}
