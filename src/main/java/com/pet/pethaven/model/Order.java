package com.pet.pethaven.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pet.pethaven.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Document(collection = "order")
public class Order {
    @Id
    private String id;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateCreated;
    private OrderStatus orderStatus;
    private Double totalPrice;
    List<OrderProduct> orderProducts;
    private UserDTO userDTO;
}
