package com.pet.pethaven.service;

import com.pet.pethaven.model.Product;
import com.pet.pethaven.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@AllArgsConstructor
public class ProductService {
    private ProductRepository productRepository;
    public Product addProduct(Product product) {
        Product productToAdd = new Product();
        productToAdd.setId(product.getId());
        productToAdd.setName(product.getName());
        productToAdd.setBrand(product.getBrand());
        productToAdd.setTargetAnimal(product.getTargetAnimal());
        productToAdd.setCategory(product.getCategory());
        productToAdd.setStockQuantity(product.getStockQuantity());
        return productRepository.save(productToAdd);
    }
}



