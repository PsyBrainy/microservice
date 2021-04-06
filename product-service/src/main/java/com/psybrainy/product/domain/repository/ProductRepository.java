package com.psybrainy.product.domain.repository;

import com.psybrainy.product.domain.CategoryRequest;
import com.psybrainy.product.domain.ProductRequest;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    List<ProductRequest> getAll();
    Optional<ProductRequest> getById(Long productId);
    Optional<List<ProductRequest>> getByCategoryId(long categoryId);
    ProductRequest save(ProductRequest productRequest);
    void delete(Long productId);
}
