package com.cimspace.stockup_product.product;

import com.cimspace.stockup_product.UUIDgenerator.UUIDgenerator;
import com.cimspace.stockup_product.product_category.ProductCategory;
import com.cimspace.stockup_product.product_category.ProductCategoryRepository;
import com.cimspace.stockup_product.productinventory.Productinventory;
import com.cimspace.stockup_product.productinventory.ProductinventoryRepository;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Transactional
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductCategoryRepository productCategoryRepository;
    private final ProductinventoryRepository productinventoryRepository;

    private UUIDgenerator uuiDgenerator= new UUIDgenerator();

    private String UUIDstring = uuiDgenerator.uuid().toString();

    public ProductService(final ProductRepository productRepository,
            final ProductCategoryRepository productCategoryRepository,
            final ProductinventoryRepository productinventoryRepository) {
        this.productRepository = productRepository;
        this.productCategoryRepository = productCategoryRepository;
        this.productinventoryRepository = productinventoryRepository;
    }

    public List<ProductDTO> findAll() {
        return productRepository.findAll()
                .stream()
                .map(product -> mapToDTO(product, new ProductDTO()))
                .collect(Collectors.toList());
    }

    public ProductDTO get(final String id) {
        return productRepository.findById(id)
                .map(product -> mapToDTO(product, new ProductDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public String create(final ProductDTO productDTO) {
        final Product product = new Product();
        mapToEntity(productDTO, product);
        return productRepository.save(product).getId();
    }

    public void update(final String id, final ProductDTO productDTO) {
        final Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(productDTO, product);
        productRepository.save(product);
    }

    public void delete(final String id) {
        productRepository.deleteById(id);
    }

    private ProductDTO mapToDTO(final Product product, final ProductDTO productDTO) {
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        productDTO.setProductCategoryId(product.getProductCategoryId() == null ? null : product.getProductCategoryId().getId());
        return productDTO;
    }

    private Product mapToEntity(final ProductDTO productDTO, final Product product) {
        product.setId(UUIDstring);
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        if (productDTO.getProductCategoryId() != null && (product.getProductCategoryId() == null || !product.getProductCategoryId().getId().equals(productDTO.getProductCategoryId()))) {
            final ProductCategory productCategoryId = productCategoryRepository.findById(productDTO.getProductCategoryId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "productCategoryId not found"));
            product.setProductCategoryId(productCategoryId);
        }
        return product;
    }

}
