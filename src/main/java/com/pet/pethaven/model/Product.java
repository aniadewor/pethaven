package com.pet.pethaven.model;

import jakarta.validation.constraints.Min;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document(collection = "product")
public record Product(
        @Id
        String id,
        String name,
        String description,
        String brand,
        @Min(1)
        Double price,
        String category,
        @Min(1)
        Integer stockQuantity,
        TargetAnimal targetAnimal,
        List<String> tags
) { public Product (String name, String description, String brand, Double price, String category, Integer stockQuantity,TargetAnimal targetAnimal,List<String>tags){
        this(null, name, description, brand, price, category, stockQuantity, targetAnimal, tags);
}
}