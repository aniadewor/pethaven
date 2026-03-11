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

        return productRepository.save(product);
    }
}



