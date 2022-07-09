package com.cimspace.stockup_order.order_items;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderItemsRepository extends JpaRepository<OrderItems, String> {
}
