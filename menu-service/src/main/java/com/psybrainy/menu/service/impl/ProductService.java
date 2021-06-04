package com.psybrainy.menu.service.impl;

import com.psybrainy.menu.constants.ConstantExceptionMessage;
import com.psybrainy.menu.crud.ProductCrudRepository;
import com.psybrainy.menu.dto.ProductRequest;
import com.psybrainy.menu.dto.ProductResponce;
import com.psybrainy.menu.model.ProductEntity;
import com.psybrainy.menu.service.IProductService;
import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService {

    @Autowired
    private ProductCrudRepository repo;

    @Autowired
    private  ModelMapper mapper;

    @Override
    public List<ProductResponce> getAll(){

        List<ProductEntity> productEntityList = repo.findAll();

        return productEntityList
                .stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponce getproduct(Long productId) throws NotFoundException {

        ProductEntity productEntity = repo.findById(productId).orElseThrow(()-> new NotFoundException(ConstantExceptionMessage.MSG_NOT_FOUND_PRODUCT));

        return entityToDto(productEntity);
    }

    @Override
    public ProductResponce save(ProductRequest productRequest) {

        ProductEntity productEntity = dtoToEntity(productRequest);

        ProductEntity productSave = repo.save(productEntity);

        return entityToDto(productSave);
    }

    @Override
    public boolean delete(Long productId) {
        return repo.findById(productId).map(productRequest -> {
            repo.deleteById(productId);
            return true;
        }).orElse(false);
    }


    public ProductResponce entityToDto(ProductEntity product){
        return mapper.map(product, ProductResponce.class);
    }

    public ProductEntity dtoToEntity(ProductRequest product){
        return mapper.map(product, ProductEntity.class);
    }
}
