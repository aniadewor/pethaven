package com.pet.pethaven.service;

import com.pet.pethaven.dto.OrderDTO;
import com.pet.pethaven.dto.OrderProductDTO;
import com.pet.pethaven.dto.UserDTO;
import com.pet.pethaven.exceptionHandler.EntityNotFoundException;
import com.pet.pethaven.model.*;
import com.pet.pethaven.repository.OrderRepository;
import com.pet.pethaven.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.hibernate.validator.cfg.defs.EmailDef;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {
    private OrderRepository orderRepository;
    private ProductRepository productRepository;
    public Order saveOrder (OrderDTO orderDTO) {

        List<OrderProduct> productsToSave = new ArrayList<>();
        double total = 0;
        for (OrderProductDTO dto : orderDTO.orderProducts()) {
            Product product = productRepository.findById(dto.productId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            OrderProduct op = new OrderProduct(dto.quantity(), product.id(), product.name(), product.price());

            productsToSave.add(op);
            total += (product.price() * dto.quantity());
        }
        Order order = new Order(LocalDate.now(),OrderStatus.CREATED, total, productsToSave, setUserDTO(orderDTO));
        return orderRepository.save(order);
    }
    public UserDTO setUserDTO (OrderDTO orderDTO) {
        UserDTO userDTO = new UserDTO(orderDTO.userDTO().email(),
                orderDTO.userDTO().firstName(),
                orderDTO.userDTO().lastName(),
                orderDTO.userDTO().phoneNumber(),
                orderDTO.userDTO().address());
        Address address = new Address(orderDTO.userDTO().address().city(),
                orderDTO.userDTO().address().country(),
                orderDTO.userDTO().address().street(),
                orderDTO.userDTO().address().zipCode(),
                orderDTO.userDTO().address().buildingNumber(),
                orderDTO.userDTO().address().apartmentNumber());
        return userDTO;
    }
    public List<Order> getOrdersByEmail (String email) {
        if (email == null) {
            throw new EntityNotFoundException("email is null");
        }
        return orderRepository.findByUserDTOEmail(email);
    }
    public Order getOrderById (String id) {
        if (id == null) {
            throw new EntityNotFoundException("id is null");
        }
        return orderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("id not found"));
    }
}
