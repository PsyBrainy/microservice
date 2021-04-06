package com.psybrainy.product.presistence;

import com.psybrainy.product.EntityException;
import com.psybrainy.product.domain.ProductRequest;
import com.psybrainy.product.domain.repository.ProductRepository;
import com.psybrainy.product.presistence.crud.CategoryCrudRepository;
import com.psybrainy.product.presistence.crud.ProductCrudRepository;
import com.psybrainy.product.presistence.entity.CategoryEntity;
import com.psybrainy.product.presistence.entity.ProductEntity;
import com.psybrainy.product.presistence.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ProductImpRepo implements ProductRepository {

    @Autowired
    private ProductCrudRepository productRepo;

    @Autowired
    private CategoryCrudRepository categoryRepo;

    @Autowired
    private Mapper mapper;


    @Override
    public List<ProductRequest> getAll(){
        List<ProductEntity> productEntities = (List<ProductEntity>) productRepo.findAll();
        return productEntities
                .stream()
                .map(productEntity -> mapper.toPorductRequest(productEntity))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ProductRequest> getById(Long productId) {
        ProductEntity productEntity =  productRepo.findById(productId).orElseThrow(EntityException::new);

        return Optional.of(mapper.toPorductRequest(productEntity));
    }

    @Override
    public Optional<List<ProductRequest>> getByCategoryId(long categoryId) {

        CategoryEntity category = categoryRepo.findById(categoryId).orElseThrow(EntityException::new);

        List<ProductEntity> productEntities = productRepo.findByCategory(category)
                .orElseThrow(EntityException::new);

        return Optional.of(productEntities
                .stream()
                .map(productEntity -> mapper.toPorductRequest(productEntity))
                .collect(Collectors.toList()));
    }

    @Override
    public ProductRequest save(ProductRequest productRequest) {
        ProductEntity productEntity = mapper.toProductEntity(productRequest);
        return mapper.toPorductRequest(productRepo.save(productEntity));
    }


    @Override
    public void delete(Long productId) {
        productRepo.deleteById(productId);
    }


}
