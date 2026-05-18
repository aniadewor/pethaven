package com.pet.pethaven.service;

import com.pet.pethaven.dto.ProductDTO;
import com.pet.pethaven.exceptionHandler.EntityNotFoundException;
import com.pet.pethaven.model.Product;
import com.pet.pethaven.model.TargetAnimal;
import com.pet.pethaven.repository.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    @DisplayName("Should successfully add a new product")
    void shouldAddProductSuccessfully() {

        // GIVEN
        ProductDTO inputProduct = new ProductDTO(
                "Karma dla psa",
                "Royal Canin",
                "Karma",
                100.0,
                "Dobra karma dla psa",
                10,
                TargetAnimal.DOG,
                List.of("premium", "dog")
        );

        Product savedProduct = new Product(
                "123",
                "Karma dla psa",
                "Royal Canin",
                "Karma",
                100.0,
                "Dobra karma dla psa",
                10,
                TargetAnimal.DOG,
                List.of("premium", "dog")
        );

        when(productRepository.save(any(Product.class)))
                .thenReturn(savedProduct);

        // WHEN
        Product result = productService.addProduct(inputProduct);

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.id()).isEqualTo("123");
        assertThat(result.name()).isEqualTo("Karma dla psa");
        assertThat(result.brand()).isEqualTo("Karma");

        verify(productRepository, times(1))
                .save(any(Product.class));
    }

    @Test
    @DisplayName("Should return list of products when products exist")
    void shouldReturnListOfProducts() {

        // GIVEN
        Product p1 = new Product(
                "1",
                "Produkt 1",
                "Pedigree",
                "Karma",
                50.0,
                "Opis 1",
                5,
                TargetAnimal.DOG,
                List.of("dog")
        );

        Product p2 = new Product(
                "2",
                "Produkt 2",
                "Whiskas",
                "Karma",
                80.0,
                "Opis 2",
                8,
                TargetAnimal.CAT,
                List.of("cat")
        );

        when(productRepository.findAll())
                .thenReturn(List.of(p1, p2));

        // WHEN
        List<Product> result = productService.getProducts();

        // THEN
        assertThat(result).hasSize(2);
        assertThat(result).containsExactly(p1, p2);

        verify(productRepository, times(1))
                .findAll();
    }

    @Test
    @DisplayName("Should throw EntityNotFoundException when product list is empty")
    void shouldThrowExceptionWhenProductsNotFound() {

        // GIVEN
        when(productRepository.findAll())
                .thenReturn(List.of());

        // WHEN & THEN
        assertThrows(EntityNotFoundException.class, () -> {
            productService.getProducts();
        });

        verify(productRepository, times(1))
                .findAll();
    }

    @Test
    @DisplayName("Should return product when valid ID is provided")
    void shouldReturnProductWhenIdExists() {

        // GIVEN
        String id = "123";

        Product product = new Product(
                id,
                "Testowy Produkt",
                "Royal Canin",
                "Karma",
                99.0,
                "Opis produktu",
                15,
                TargetAnimal.DOG,
                List.of("premium")
        );

        when(productRepository.findById(id))
                .thenReturn(Optional.of(product));

        // WHEN
        Product result = productService.getProduct(id);

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.id()).isEqualTo(id);
        assertThat(result.name()).isEqualTo("Testowy Produkt");

        verify(productRepository, times(1))
                .findById(id);
    }

    @Test
    @DisplayName("Should throw EntityNotFoundException when ID is null")
    void shouldThrowExceptionWhenIdIsNull() {

        // WHEN & THEN
        assertThrows(EntityNotFoundException.class, () -> {
            productService.getProduct(null);
        });

        verifyNoInteractions(productRepository);
    }

    @Test
    @DisplayName("Should throw exception when product does not exist in DB")
    void shouldThrowExceptionWhenProductNotFoundInDb() {

        // GIVEN
        String id = "nie-istnieje";

        when(productRepository.findById(id))
                .thenReturn(Optional.empty());

        // WHEN & THEN
        assertThrows(NoSuchElementException.class, () -> {
            productService.getProduct(id);
        });

        verify(productRepository, times(1))
                .findById(id);
    }
}