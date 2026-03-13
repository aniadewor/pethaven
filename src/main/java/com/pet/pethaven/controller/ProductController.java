package com.pet.pethaven.controller;

import com.pet.pethaven.model.Product;
import com.pet.pethaven.repository.ProductRepository;
import com.pet.pethaven.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/products")
@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/addProduct")
    public ResponseEntity<Product> addProduct(@Validated @RequestBody Product product) {
       productService.addProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }
    @GetMapping("/getProducts")
    public ResponseEntity<List<Product>> getProducts() {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProducts());
    }
}
