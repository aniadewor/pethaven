package com.pet.pethaven.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;

@Validated
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Document(collection = "product")
public class Product {
    @Id
    private String id;
    @NotNull(message = "Name cannot be null")
    private String name;
    @NotNull(message = "Brand cannot be null")
    private String brand;
    @Min(value = 1)
    @NotNull(message = "Price cannot be null")
    private Integer price;
    @NotNull(message = "Category cannot be null")
    private String category;
    @Min(value = 1)
    @NotNull(message = "stockQuantity cannot be null")
    private Integer stockQuantity;
    @NotNull(message = "targetAnimal cannot be null")
    private TargetAnimal targetAnimal;
    List<String> tags = new ArrayList<>();
}
