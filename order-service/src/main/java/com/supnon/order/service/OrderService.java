package com.supnon.order.service;

import com.supnon.order.entity.Order;
import com.supnon.order.entity.OrderItem;
import com.supnon.order.repository.OrderRepository;
import com.supnon.order.repository.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
    }

    @Transactional
    public Order createOrder(Order order) {
        double totalAmount = order.getOrderItems().stream()
                .mapToDouble(OrderItem::getSubtotal)
                .sum();
        order.setTotalAmount(totalAmount);
        order.getOrderItems().forEach(item -> item.setOrder(order));

        return orderRepository.save(order);
    }

    @Transactional
    public Order updateOrder(Long id, Order orderDetails) {
        Order order = getOrderById(id);
        
        order.setCustomerName(orderDetails.getCustomerName());
        order.setCustomerPhone(orderDetails.getCustomerPhone());
        order.setDeliveryAddress(orderDetails.getDeliveryAddress());
        order.setStatus(orderDetails.getStatus());

        // Update order items if provided
        if (orderDetails.getOrderItems() != null) {
            // Remove existing items
            order.getOrderItems().forEach(orderItemRepository::delete);
            
            // Add new items
            orderDetails.getOrderItems().forEach(item -> {
                item.setOrder(order);
                orderItemRepository.save(item);
            });
            
            // Recalculate total amount
            double totalAmount = orderDetails.getOrderItems().stream()
                    .mapToDouble(OrderItem::getSubtotal)
                    .sum();
            order.setTotalAmount(totalAmount);
        }

        return orderRepository.save(order);
    }

    public void deleteOrder(Long id) {
        Order order = getOrderById(id);
        orderRepository.delete(order);
    }
} 