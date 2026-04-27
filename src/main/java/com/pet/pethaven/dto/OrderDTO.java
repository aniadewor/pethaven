package com.pet.pethaven.dto;

import com.pet.pethaven.model.OrderProduct;
import com.pet.pethaven.model.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderDTO {
    private Double totalPrice;
    private List<OrderProductDTO> orderProducts;
    private UserDTO userDTO;
}
