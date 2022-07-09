package com.cimspace.stockup_product.product_category;

import java.util.List;
import java.util.stream.Collectors;

import com.cimspace.stockup_product.UUIDgenerator.UUIDgenerator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class ProductCategoryService {

    private final ProductCategoryRepository productCategoryRepository;

    private UUIDgenerator uuiDgenerator= new UUIDgenerator();

    private String UUIDstring = uuiDgenerator.uuid().toString();

    public ProductCategoryService(final ProductCategoryRepository productCategoryRepository) {
        this.productCategoryRepository = productCategoryRepository;
    }

    public List<ProductCategoryDTO> findAll() {
        return productCategoryRepository.findAll()
                .stream()
                .map(productCategory -> mapToDTO(productCategory, new ProductCategoryDTO()))
                .collect(Collectors.toList());
    }

    public ProductCategoryDTO get(final String id) {
        return productCategoryRepository.findById(id)
                .map(productCategory -> mapToDTO(productCategory, new ProductCategoryDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public String create(final ProductCategoryDTO productCategoryDTO) {
        final ProductCategory productCategory = new ProductCategory();
        mapToEntity(productCategoryDTO, productCategory);
        return productCategoryRepository.save(productCategory).getId();
    }

    public void update(final String id, final ProductCategoryDTO productCategoryDTO) {
        final ProductCategory productCategory = productCategoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(productCategoryDTO, productCategory);
        productCategoryRepository.save(productCategory);
    }

    public void delete(final String id) {
        productCategoryRepository.deleteById(id);
    }

    private ProductCategoryDTO mapToDTO(final ProductCategory productCategory,
            final ProductCategoryDTO productCategoryDTO) {
        productCategoryDTO.setId(productCategory.getId());
        productCategoryDTO.setName(productCategory.getName());
        productCategoryDTO.setDescription(productCategory.getDescription());
        return productCategoryDTO;
    }

    private ProductCategory mapToEntity(final ProductCategoryDTO productCategoryDTO,
            final ProductCategory productCategory) {
        productCategory.setId(UUIDstring);
        productCategory.setName(productCategoryDTO.getName());
        productCategory.setDescription(productCategoryDTO.getDescription());
        return productCategory;
    }

}
