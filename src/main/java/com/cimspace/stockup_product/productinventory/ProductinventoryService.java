package com.cimspace.stockup_product.productinventory;

import java.util.List;
import java.util.stream.Collectors;

import com.cimspace.stockup_product.UUIDgenerator.UUIDgenerator;
import com.cimspace.stockup_product.product.Product;
import com.cimspace.stockup_product.product.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class ProductinventoryService {

    private final ProductinventoryRepository productinventoryRepository;

    private final ProductRepository productRepository;

    private UUIDgenerator uuiDgenerator= new UUIDgenerator();

    private String UUIDstring = uuiDgenerator.uuid().toString();

    public ProductinventoryService(final ProductinventoryRepository productinventoryRepository, final ProductRepository productRepository) {
        this.productinventoryRepository = productinventoryRepository;
        this.productRepository = productRepository;
    }

    public List<ProductinventoryDTO> findAll() {
        return productinventoryRepository.findAll()
                .stream()
                .map(productinventory -> mapToDTO(productinventory, new ProductinventoryDTO()))
                .collect(Collectors.toList());
    }

    public ProductinventoryDTO get(final String id) {
        return productinventoryRepository.findById(id)
                .map(productinventory -> mapToDTO(productinventory, new ProductinventoryDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public String create(final ProductinventoryDTO productinventoryDTO) {
        final Productinventory productinventory = new Productinventory();
        mapToEntity(productinventoryDTO, productinventory);
        return productinventoryRepository.save(productinventory).getId();
    }

    public void update(final String id, final ProductinventoryDTO productinventoryDTO) {
        final Productinventory productinventory = productinventoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(productinventoryDTO, productinventory);
        productinventoryRepository.save(productinventory);
    }

    public void delete(final String id) {
        productinventoryRepository.deleteById(id);
    }

    private ProductinventoryDTO mapToDTO(final Productinventory productinventory,
            final ProductinventoryDTO productinventoryDTO) {
        productinventoryDTO.setId(productinventory.getId());
        productinventoryDTO.setQuantity(productinventory.getQuantity());
        productinventoryDTO.setProductSize(productinventory.getProductSize());
        productinventoryDTO.setProductid(productinventory.getProductid() == null ? null : productinventory.getProductid().getId());
        return productinventoryDTO;
    }

    private Productinventory mapToEntity(final ProductinventoryDTO productinventoryDTO,
            final Productinventory productinventory) {
        productinventory.setId(UUIDstring);
        productinventory.setQuantity(productinventoryDTO.getQuantity());
        productinventory.setProductSize(productinventoryDTO.getProductSize());
        if (productinventoryDTO.getProductid() != null && (productinventory.getProductid() == null || !productinventory.getProductid().getId().equals(productinventoryDTO.getProductid()))) {
            final Product productid = productRepository.findById(productinventoryDTO.getProductid())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "productid not found"));
            productinventory.setProductid(productid);
        }
        return productinventory;

    }

}
