package com.cimspace.stockup_order.order_items;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.cimspace.stockup_order.UUIDgenerator.UUIDgenerator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class OrderItemsService {

    private final OrderItemsRepository orderItemsRepository;

    private UUIDgenerator uuiDgenerator= new UUIDgenerator();

    private String UUIDstring = uuiDgenerator.uuid().toString();

    public OrderItemsService(final OrderItemsRepository orderItemsRepository) {
        this.orderItemsRepository = orderItemsRepository;
    }

    public List<OrderItemsDTO> findAll() {
        return orderItemsRepository.findAll()
                .stream()
                .map(orderItems -> mapToDTO(orderItems, new OrderItemsDTO()))
                .collect(Collectors.toList());
    }

    public OrderItemsDTO get(final String id) {
        return orderItemsRepository.findById(id)
                .map(orderItems -> mapToDTO(orderItems, new OrderItemsDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public String create(final OrderItemsDTO orderItemsDTO) {
        final OrderItems orderItems = new OrderItems();
        mapToEntity(orderItemsDTO, orderItems);
        return orderItemsRepository.save(orderItems).getId();
    }

    public void update(final String id, final OrderItemsDTO orderItemsDTO) {
        final OrderItems orderItems = orderItemsRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(orderItemsDTO, orderItems);
        orderItemsRepository.save(orderItems);
    }

    public void delete(final String id) {
        orderItemsRepository.deleteById(id);
    }

    private OrderItemsDTO mapToDTO(final OrderItems orderItems, final OrderItemsDTO orderItemsDTO) {
        orderItemsDTO.setId(orderItems.getId());
        orderItemsDTO.setQuantity(orderItems.getQuantity());
        orderItemsDTO.setTotal(orderItems.getTotal());
        orderItemsDTO.setProductId(orderItems.getProductId());
        orderItemsDTO.setUserId(orderItems.getUserId());
        orderItemsDTO.setItemCollected(orderItems.getItemCollected());
        return orderItemsDTO;
    }

    private OrderItems mapToEntity(final OrderItemsDTO orderItemsDTO, final OrderItems orderItems) {
        orderItems.setId(UUIDstring);
        orderItems.setQuantity(orderItemsDTO.getQuantity());
        orderItems.setTotal(orderItemsDTO.getTotal());
        orderItems.setProductId(orderItemsDTO.getProductId());
        orderItems.setUserId(orderItemsDTO.getUserId());
        orderItems.setItemCollected(orderItemsDTO.getItemCollected());
        return orderItems;
    }

}
