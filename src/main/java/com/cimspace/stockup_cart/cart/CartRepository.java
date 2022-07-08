package com.cimspace.stockup_cart.cart;

import org.springframework.data.jpa.repository.JpaRepository;


public interface CartRepository extends JpaRepository<Cart, String> {
}
