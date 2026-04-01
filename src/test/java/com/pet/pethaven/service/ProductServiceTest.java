package com.pet.pethaven.service;

import com.pet.pethaven.dto.ProductDTO;
import com.pet.pethaven.exceptionHandler.EntityNotFoundException;
import com.pet.pethaven.model.Product;
import com.pet.pethaven.repository.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

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
    @Test
    @DisplayName("Should return list of products when products exist")
    void shouldReturnListOfProducts() {
        // GIVEN
        Product p1 = new Product();
        p1.setName("Produkt 1");
        Product p2 = new Product();
        p2.setName("Produkt 2");

        when(productRepository.findAll()).thenReturn(List.of(p1, p2));

        // WHEN
        List<Product> result = productService.getProducts();

        // THEN
        assertThat(result).hasSize(2);
        assertThat(result).containsExactly(p1, p2);
        verify(productRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should throw EntityNotFoundException when product list is empty")
    void shouldThrowExceptionWhenProductsNotFound() {
        // GIVEN
        when(productRepository.findAll()).thenReturn(List.of());

        // WHEN & THEN
        org.junit.jupiter.api.Assertions.assertThrows(EntityNotFoundException.class, () -> {
            productService.getProducts();
        });

        verify(productRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should return product when valid ID is provided")
    void shouldReturnProductWhenIdExists() {
        // GIVEN
        String id = "123";
        Product product = new Product();
        product.setId(id);
        product.setName("Testowy Produkt");

        // W repository.findById zwracamy Optional
        when(productRepository.findById(id)).thenReturn(java.util.Optional.of(product));

        // WHEN
        Product result = productService.getProduct(id);

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(id);
        verify(productRepository, times(1)).findById(id);
    }

    @Test
    @DisplayName("Should throw EntityNotFoundException when ID is null")
    void shouldThrowExceptionWhenIdIsNull() {
        // WHEN & THEN
        org.junit.jupiter.api.Assertions.assertThrows(EntityNotFoundException.class, () -> {
            productService.getProduct(null);
        });

        // Weryfikujemy, że repozytorium w ogóle nie zostało zapytane
        verifyNoInteractions(productRepository);
    }

    @Test
    @DisplayName("Should throw exception when product does not exist in DB")
    void shouldThrowExceptionWhenProductNotFoundInDb() {
        // GIVEN
        String id = "nie-istnieje";
        when(productRepository.findById(id)).thenReturn(java.util.Optional.empty());

        // WHEN & THEN
        org.junit.jupiter.api.Assertions.assertThrows(java.util.NoSuchElementException.class, () -> {
            productService.getProduct(id);
        });
    }
}