package com.pet.pethaven.controller;

import com.pet.pethaven.ProductDTO;
import com.pet.pethaven.model.Product;
import com.pet.pethaven.repository.ProductRepository;
import com.pet.pethaven.service.ProductService;
import jakarta.validation.Valid;
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
    public ResponseEntity<Product> addProduct(@Valid @RequestBody ProductDTO dto) {
       productService.addProduct(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }
    @GetMapping("/getProducts")
    public ResponseEntity<List<Product>> getProducts() {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProducts());
    }
    @GetMapping("/getProduct")
    public ResponseEntity<Product> getProduct(@RequestParam("id") String id) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProduct(id));
    }
}
