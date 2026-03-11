package com.pet.pethaven.repository;

import com.pet.pethaven.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
