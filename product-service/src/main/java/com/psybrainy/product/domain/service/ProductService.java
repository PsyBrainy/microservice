package com.psybrainy.product.domain.service;

import com.psybrainy.product.domain.ProductRequest;
import com.psybrainy.product.domain.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private ProductRepository repo;

    public List<ProductRequest> getAll(){
        return repo.getAll();
    }

    public Optional<ProductRequest> getproduct(Long productId) {
        return repo.getById(productId);
    }

    public Optional<List<ProductRequest>> getByCategoryId(Long categoryId) {
        return repo.getByCategoryId(categoryId);
    }

    public ProductRequest save(ProductRequest productRequest) {
        return repo.save(productRequest);
    }

    public boolean delete(Long productId) {
        return getproduct(productId).map(productRequest -> {
            repo.delete(productId);
            return true;
        }).orElse(false);
    }
}
