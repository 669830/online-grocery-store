package com.grocerystore.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record ProductRequest (
        @NotBlank(message="Produktnavn er påkrevd")
        String name,
        String description,

        @NotNull(message = "Pris er påkrevd")
        @Positive(message = "Pris må være større enn 0")
        BigDecimal price,

        @NotNull(message = "Lagerantall er påkrevd")
        @PositiveOrZero(message = "lagerantall kan ikke være negativt")
        Integer stockQuantity,

        String category,
        String imageUrl
){
}
