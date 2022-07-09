package com.cimspace.stockup_product.productinventory;

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
@RequestMapping(value = "/api/productinventory", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductinventoryController {

    private final ProductinventoryService productinventoryService;

    public ProductinventoryController(final ProductinventoryService productinventoryService) {
        this.productinventoryService = productinventoryService;
    }

    @GetMapping
    public ResponseEntity<List<ProductinventoryDTO>> getAllProductinventorys() {
        return ResponseEntity.ok(productinventoryService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductinventoryDTO> getProductinventory(@PathVariable final String id) {
        return ResponseEntity.ok(productinventoryService.get(id));
    }

    @PostMapping
    public ResponseEntity<String> createProductinventory(
            @RequestBody @Valid final ProductinventoryDTO productinventoryDTO) {
        return new ResponseEntity<>(productinventoryService.create(productinventoryDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateProductinventory(@PathVariable final String id,
            @RequestBody @Valid final ProductinventoryDTO productinventoryDTO) {
        productinventoryService.update(id, productinventoryDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductinventory(@PathVariable final String id) {
        productinventoryService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
