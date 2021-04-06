package com.psybrainy.product.domain.repository;

import com.psybrainy.product.domain.ProductRequest;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    List<ProductRequest> getAll();
    Optional<ProductRequest> getById(Long productId);
    Optional<List<ProductRequest>> getByCategoryId(Long categoryId);
    ProductRequest save(ProductRequest productRequest);
    void delete(Long productId);
}
