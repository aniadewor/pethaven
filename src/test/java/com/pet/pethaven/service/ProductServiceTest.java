package com.pet.pethaven.service;

import com.pet.pethaven.dto.ProductDTO;
import com.pet.pethaven.model.Product;
import com.pet.pethaven.repository.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // Inicjalizuje mocki Mockito
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository; // Udajemy bazę danych

    @InjectMocks
    private ProductService productService; // Klasa, którą testujemy (wstrzykuje do niej mocka repozytorium)

    @Test
    @DisplayName("Should successfully add a new product")
    void shouldAddProductSuccessfully() {
        // GIVEN: Przygotowanie danych wejściowych
        ProductDTO inputProduct = new ProductDTO();
        inputProduct.setName("Karma dla psa");
        inputProduct.setPrice(100.0);

        Product savedProduct = new Product();
        savedProduct.setId("123"); // Symulujemy, że baza nadała ID
        savedProduct.setName("Karma dla psa");
        savedProduct.setPrice(100.0);

        // Definiujemy zachowanie mocka: gdy ktoś zawoła save, zwróć savedProduct
        when(productRepository.save(any(Product.class))).thenReturn(savedProduct);

        // WHEN: Wywołanie metody, którą testujemy
        Product result = productService.addProduct(inputProduct);

        // THEN: Weryfikacja wyników (AssertJ)
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo("123");
        assertThat(result.getName()).isEqualTo("Karma dla psa");

        // Weryfikacja, czy repozytorium zostało faktycznie wywołane dokładnie raz
        verify(productRepository, times(1)).save(any(Product.class));
    }
}