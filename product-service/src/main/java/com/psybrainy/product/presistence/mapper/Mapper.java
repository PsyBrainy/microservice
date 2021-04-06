package com.psybrainy.product.presistence.mapper;

import com.psybrainy.product.EntityException;
import com.psybrainy.product.domain.ProductRequest;
import com.psybrainy.product.presistence.crud.CategoryCrudRepository;
import com.psybrainy.product.presistence.entity.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    @Autowired
    private CategoryCrudRepository repo;


    public ProductRequest toPorductRequest(ProductEntity productEntity){

        return ProductRequest.builder()
                .porductId(productEntity.getPorductId())
                .name(productEntity.getName())
                .description(productEntity.getDescription())
                .price(productEntity.getPrice())
                .status(productEntity.getStatus())
                .category(productEntity.getCategory().getName())
                .build();
    }

    public ProductEntity toProductEntity(ProductRequest productRequest){

        return ProductEntity.builder()
                .porductId(productRequest.getPorductId())
                .name(productRequest.getName())
                .price(productRequest.getPrice())
                .description(productRequest.getDescription())
                .status(productRequest.getStatus())
                .category(repo.findByName(productRequest.getCategory()).orElseThrow(EntityException::new))
                .build();
    }

}
