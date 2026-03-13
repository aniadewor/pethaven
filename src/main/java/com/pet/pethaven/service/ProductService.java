package com.pet.pethaven.service;

import com.pet.pethaven.exceptionHandler.EntityNotFoundException;
import com.pet.pethaven.model.Product;
import com.pet.pethaven.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {
    private ProductRepository productRepository;
    public Product addProduct(Product product) {

        return productRepository.save(product);
    }
    public List<Product> getProducts(){
        List<Product> productList = productRepository.findAll();
        if(productList.isEmpty()){
            throw new EntityNotFoundException("Products not found");
        }
        return productList;
    }
    public Product getProduct(String id){
        if(id == null){
            throw new EntityNotFoundException("Product id not found");
        }
        Product product = productRepository.findById(id).get();
        if(product == null){
            throw new EntityNotFoundException("Product not found");
        }
        return product;
    }
}



