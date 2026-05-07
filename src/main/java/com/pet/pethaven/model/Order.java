package com.pet.pethaven.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pet.pethaven.dto.UserDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Document(collection = "order")
public record Order(
        @Id
        String id,
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate dateCreated,
        OrderStatus orderStatus,
        Double totalPrice,
        List<OrderProduct> orderProducts,
        UserDTO userDTO
) {
        public Order (LocalDate dateCreated, OrderStatus orderStatus, Double totalPrice, List<OrderProduct> orderProducts, UserDTO userDTO) {
                this(null, dateCreated, orderStatus, totalPrice,orderProducts, userDTO);
        }
}