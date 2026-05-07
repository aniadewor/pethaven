package com.pet.pethaven.service;

import com.pet.pethaven.dto.OrderDTO;
import com.pet.pethaven.dto.OrderProductDTO;
import com.pet.pethaven.dto.UserDTO;
import com.pet.pethaven.exceptionHandler.EntityNotFoundException;
import com.pet.pethaven.model.*;
import com.pet.pethaven.repository.OrderRepository;
import com.pet.pethaven.repository.ProductRepository;
import lombok.AllArgsConstructor;
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
        for (OrderProductDTO dto : orderDTO.getOrderProducts()) {
            Product product = productRepository.findById(dto.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            OrderProduct op = new OrderProduct(dto.getQuantity(), product.id(), product.name(), product.price());

            productsToSave.add(op);
            total += (product.price() * dto.getQuantity());
        }
        Order order = new Order(LocalDate.now(),OrderStatus.CREATED, total, productsToSave, setUserDTO(orderDTO));
        return orderRepository.save(order);
    }
    public UserDTO setUserDTO (OrderDTO orderDTO) {
        UserDTO userDTO = new UserDTO();
        Address address = new Address(orderDTO.getUserDTO().getAddress().city(),
                orderDTO.getUserDTO().getAddress().country(),
                orderDTO.getUserDTO().getAddress().street(),
                orderDTO.getUserDTO().getAddress().zipCode(),
                orderDTO.getUserDTO().getAddress().buildingNumber(),
                orderDTO.getUserDTO().getAddress().apartmentNumber());
        userDTO.setEmail(orderDTO.getUserDTO().getEmail());
        userDTO.setFirstName(orderDTO.getUserDTO().getFirstName());
        userDTO.setLastName(orderDTO.getUserDTO().getLastName());
        userDTO.setPhoneNumber(orderDTO.getUserDTO().getPhoneNumber());
        userDTO.setAddress(address);
        userDTO.setAddress(address);
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
