package com.pet.pethaven;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductDTO {
    @NotBlank(message = "Nazwa produktu nie może być pusta")
    private String name;

    @NotBlank(message = "Marka produktu jest wymagana")
    private String brand;

    private String category;

    @NotNull(message = "Ilość na stanie nie może być pusta")
    @Min(value = 1, message = "Minimalna ilość na stanie to 1")
    private Integer stockQuantity;
    private String targetAnimal;
    private List<String> tags;

}
