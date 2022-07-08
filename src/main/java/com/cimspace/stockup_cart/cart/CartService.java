package com.cimspace.stockup_cart.cart;

import java.util.List;
import java.util.stream.Collectors;


import com.cimspace.stockup_cart.UUIDgenerator.UUIDgenerator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CartService {

    private final CartRepository cartRepository;

    private UUIDgenerator uuiDgenerator= new UUIDgenerator();

    private String UUIDstring = uuiDgenerator.uuid().toString();

    public CartService(final CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public List<CartDTO> findAll() {
        return cartRepository.findAll()
                .stream()
                .map(cart -> mapToDTO(cart, new CartDTO()))
                .collect(Collectors.toList());
    }

    public CartDTO get(final String id) {
        return cartRepository.findById(id)
                .map(cart -> mapToDTO(cart, new CartDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public String create(final CartDTO cartDTO) {
        final Cart cart = new Cart();
        mapToEntity(cartDTO, cart);
        return cartRepository.save(cart).getId();
    }

    public void update(final String id, final CartDTO cartDTO) {
        final Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(cartDTO, cart);
        cartRepository.save(cart);
    }

    public void deletebyid(final String id) {
        cartRepository.deleteById(id);
    }

    public void delete() {
        cartRepository.deleteAll();
    }

    private CartDTO mapToDTO(final Cart cart, final CartDTO cartDTO) {
        cartDTO.setId(cart.getId());
        cartDTO.setQuantity(cart.getQuantity());
        cartDTO.setProductid(cart.getProductid());
        cartDTO.setPurchased(cart.getPurchased());
        cartDTO.setTotal(cart.getTotal());
        cartDTO.setUserid(cart.getUserid());
        return cartDTO;
    }

    private Cart mapToEntity(final CartDTO cartDTO, final Cart cart) {
        cart.setId(UUIDstring);
        cart.setQuantity(cartDTO.getQuantity());
        cart.setProductid(cartDTO.getProductid());
        cart.setPurchased(cartDTO.getPurchased());
        cart.setTotal(cartDTO.getTotal());
        cart.setUserid(cartDTO.getUserid());
        return cart;
    }

}
