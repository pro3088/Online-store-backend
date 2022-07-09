package com.cimspace.stockup_product.product_category;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ProductCategoryDTO {

    private String id;

    @NotNull
    @Size(max = 255)
    private String name;

    private String description;

}
