package com.grocerystore.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Produktnavn er påkrevd")
    @Column(nullable = false)
    private String name;

    @Column(length = 1000)
    private String description;

    @NotNull(message = "Pris er påkrevd")
    @Positive(message = "Pris må være større enn 0")
    @Column(nullable = false)
    private BigDecimal price;

    @NotNull(message = "Lagerantall er påkrevd")
    @PositiveOrZero(message = "Lagerantall kan ikke være negativt")
    @Column(nullable = false)
    private Integer stockQuantity;

    private String category;
    private String imageUrl;
}