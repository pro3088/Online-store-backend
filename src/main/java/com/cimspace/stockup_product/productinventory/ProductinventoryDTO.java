package com.cimspace.stockup_product.productinventory;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ProductinventoryDTO {

    private String id;

    @NotNull
    private Integer quantity;

    @NotNull
    @Size(max = 255)
    private String productSize;

    @NotNull
    private String productid;

}
