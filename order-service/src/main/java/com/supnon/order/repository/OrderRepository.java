package com.supnon.order.repository;

import com.supnon.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
} 