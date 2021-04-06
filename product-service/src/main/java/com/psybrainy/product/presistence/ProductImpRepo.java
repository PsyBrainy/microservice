package com.psybrainy.product.presistence;

import com.psybrainy.product.EntityException;
import com.psybrainy.product.domain.ProductRequest;
import com.psybrainy.product.domain.repository.ProductRepository;
import com.psybrainy.product.presistence.crud.ProductCrudRepository;
import com.psybrainy.product.presistence.entity.ProductEntity;
import com.psybrainy.product.presistence.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ProductImpRepo implements ProductRepository {

    @Autowired
    ProductCrudRepository repo;

    @Autowired
    ProductMapper mapper;

    @Override
    public List<ProductRequest> getAll(){
        List<ProductEntity> productEntities = (List<ProductEntity>) repo.findAll();
        return productEntities
                .stream()
                .map(productEntity -> mapper.toPorductRequest(productEntity))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ProductRequest> getById(Long productId) {
        ProductEntity productEntity =  repo.findById(productId).orElseThrow(EntityException::new);

        return Optional.of(mapper.toPorductRequest(productEntity));
    }

    @Override
    public Optional<List<ProductRequest>> getByCategoryId(Long categoryId) {
        List<ProductEntity> productEntities = repo.findByCategoryId(categoryId)
                .orElseThrow(EntityException::new);

        return Optional.of(productEntities
                .stream()
                .map(productEntity -> mapper.toPorductRequest(productEntity))
                .collect(Collectors.toList()));
    }

    @Override
    public ProductRequest save(ProductRequest productRequest) {
        ProductEntity productEntity = mapper.toProductEntity(productRequest);
        return mapper.toPorductRequest(repo.save(productEntity));
    }


    @Override
    public void delete(Long productId) {
        repo.deleteById(productId);
    }


}
