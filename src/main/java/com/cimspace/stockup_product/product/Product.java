package com.cimspace.stockup_product.product;

import com.cimspace.stockup_product.product_category.ProductCategory;
import com.cimspace.stockup_product.productinventory.Productinventory;
import java.math.BigDecimal;
import java.util.Set;
import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Product {

    @Id
    @Column(nullable = false, updatable = false)
    private String id;

    @Column(nullable = false, unique = true, length = 200)
    private String name;

    @Column(columnDefinition = "text")
    private String description;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_category_id_id", nullable = false)
    private ProductCategory productCategoryId;

    @OneToMany(mappedBy = "productid")
    private Set<Productinventory> productid;

}
