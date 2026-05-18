package com.pet.pethaven.dto;

import com.pet.pethaven.model.TargetAnimal;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

public record ProductDTO (
    @NotBlank(message = "Nazwa produktu nie może być pusta")
    String name,
    @NotBlank(message = "Marka produktu jest wymagana")
     String brand,
     String category,
     Double price,
     String description,
    @NotNull(message = "Ilość na stanie nie może być pusta")
    @Min(value = 1, message = "Minimalna ilość na stanie to 1")
     Integer stockQuantity,
     TargetAnimal targetAnimal,
     List<String> tags
){}
