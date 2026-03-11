package com.pet.pethaven.model;

import jakarta.validation.constraints.Min;
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
    private String name;
    private String brand;
    @Min(value = 1)
    private Integer price;
    private String category;
    @Min(value = 1)
    private Integer stockQuantity;
    private TargetAnimal targetAnimal;
    List<String> tags = new ArrayList<>();





}
