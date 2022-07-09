package com.cimspace.stockup_product.productinventory;

import com.cimspace.stockup_product.product.Product;
import java.util.Set;
import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Productinventory {

    @Id
    @Column(nullable = false, updatable = false)
    private String id;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private String productSize;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productid_id", nullable = false)
    private Product productid;

}
