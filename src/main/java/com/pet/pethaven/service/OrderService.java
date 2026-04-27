package com.pet.pethaven.service;

import com.pet.pethaven.dto.OrderDTO;
import com.pet.pethaven.dto.OrderProductDTO;
import com.pet.pethaven.dto.UserDTO;
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
        Order order = new Order();
        order.setDateCreated(LocalDate.now());
        order.setOrderStatus(OrderStatus.CREATED);

        List<OrderProduct> productsToSave = new ArrayList<>();
        double total = 0;
        for (OrderProductDTO dto : orderDTO.getOrderProducts()) {
            Product product = productRepository.findById(dto.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            OrderProduct op = new OrderProduct();
            op.setProductId(product.getId());
            op.setProductName(product.getName());
            op.setProductPrice(product.getPrice());
            op.setQuantity(dto.getQuantity());

            productsToSave.add(op);
            total += (product.getPrice() * dto.getQuantity());
        }
        order.setTotalPrice(total);
        order.setOrderProducts(productsToSave);
        order.setUserDTO(setUserDTO(orderDTO));
        return orderRepository.save(order);
    }
    public UserDTO setUserDTO (OrderDTO orderDTO) {
        UserDTO userDTO = new UserDTO();
        Address address = new Address();
        userDTO.setEmail(orderDTO.getUserDTO().getEmail());
        userDTO.setFirstName(orderDTO.getUserDTO().getFirstName());
        userDTO.setLastName(orderDTO.getUserDTO().getLastName());
        userDTO.setPhoneNumber(orderDTO.getUserDTO().getPhoneNumber());
        userDTO.setAddress(address);
        address.setCity(orderDTO.getUserDTO().getAddress().getCity());
        address.setCountry(orderDTO.getUserDTO().getAddress().getCountry());
        address.setStreet(orderDTO.getUserDTO().getAddress().getStreet());
        address.setZipCode(orderDTO.getUserDTO().getAddress().getZipCode());
        address.setBuildingNumber(orderDTO.getUserDTO().getAddress().getBuildingNumber());
        address.setApartmentNumber(orderDTO.getUserDTO().getAddress().getApartmentNumber());
        userDTO.setAddress(address);
        return userDTO;
    }
}
