package com.pet.pethaven.service;

import com.pet.pethaven.dto.OrderDTO;
import com.pet.pethaven.dto.OrderProductDTO;
import com.pet.pethaven.model.Order;
import com.pet.pethaven.model.OrderProduct;
import com.pet.pethaven.model.OrderStatus;
import com.pet.pethaven.model.Product;
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
        return orderRepository.save(order);
    }
}
