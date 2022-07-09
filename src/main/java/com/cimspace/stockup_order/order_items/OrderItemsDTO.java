package com.cimspace.stockup_order.order_items;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class OrderItemsDTO {

    private String id;

    @NotNull
    private Integer quantity;

    @Digits(integer = 10, fraction = 2)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Schema(type = "string", example = "28.08")
    private BigDecimal total;

    @NotNull
    private String productId;

    @NotNull
    private String userId;

    private Boolean itemCollected;

}
