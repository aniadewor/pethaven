package com.pet.pethaven.dto;

import com.pet.pethaven.model.Address;

public record UserDTO (
     String firstName,
     String lastName,
     String email,
     String phoneNumber,
     Address address
){}
