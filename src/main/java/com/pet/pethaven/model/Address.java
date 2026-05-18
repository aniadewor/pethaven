package com.pet.pethaven.model;

public record Address(
        String street,
        String city,
        String zipCode,
        String country,
        Integer buildingNumber,
        Integer apartmentNumber
) {}