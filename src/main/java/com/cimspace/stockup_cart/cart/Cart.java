package com.cimspace.stockup_cart.cart;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Cart {

    @Id
    @Column(nullable = false, updatable = false)
    private String id;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Long productid;

    @Column(nullable = false)
    private Long userid;

    @Column
    private Boolean purchased;

    @Column(precision = 10, scale = 2)
    private BigDecimal total;

}
