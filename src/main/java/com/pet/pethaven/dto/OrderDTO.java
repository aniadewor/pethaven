package com.pet.pethaven.dto;
import java.util.List;

public record OrderDTO (
    Double totalPrice,
  List<OrderProductDTO> orderProducts,
    UserDTO userDTO
){}
