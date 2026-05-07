package com.pet.pethaven.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "orderProduct")
public record OrderProduct(
        Integer quantity,
        String productId,
        String productName,
        Double productPrice
) {
}