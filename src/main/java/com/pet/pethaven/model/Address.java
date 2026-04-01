package com.pet.pethaven.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Address {
    private String street;
    private String city;
    private String zipCode;
    private String country;
    private Integer buildingNumber;
    private Integer apartmentNumber;
}
