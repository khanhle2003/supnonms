package com.supnon.order.repository;

import com.supnon.order.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
} 