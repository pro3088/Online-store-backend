package com.cimspace.stockup_order.order_items;

import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/order", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderItemsController {

    private final OrderItemsService orderItemsService;

    public OrderItemsController(final OrderItemsService orderItemsService) {
        this.orderItemsService = orderItemsService;
    }

    @GetMapping
    public ResponseEntity<List<OrderItemsDTO>> getAllOrderItemss() {
        return ResponseEntity.ok(orderItemsService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderItemsDTO> getOrderItems(@PathVariable final String id) {
        return ResponseEntity.ok(orderItemsService.get(id));
    }

    @PostMapping
    public ResponseEntity<String> createOrderItems(
            @RequestBody @Valid final OrderItemsDTO orderItemsDTO) {
        return new ResponseEntity<>(orderItemsService.create(orderItemsDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateOrderItems(@PathVariable final String id,
            @RequestBody @Valid final OrderItemsDTO orderItemsDTO) {
        orderItemsService.update(id, orderItemsDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderItems(@PathVariable final String id) {
        orderItemsService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
