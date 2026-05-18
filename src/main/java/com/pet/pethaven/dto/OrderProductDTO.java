package com.pet.pethaven.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public record OrderProductDTO (
     Integer quantity,
   String productId
){}
