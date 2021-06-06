package com.psybrainy.menu.service.impl;

import com.psybrainy.menu.constants.ConstantExceptionMessage;
import com.psybrainy.menu.crud.CategoryCrudRepository;
import com.psybrainy.menu.crud.ProductCrudRepository;
import com.psybrainy.menu.dto.CategoryResponce;
import com.psybrainy.menu.dto.ProductRequest;
import com.psybrainy.menu.dto.ProductResponce;
import com.psybrainy.menu.exception.custom.BadRequestException;
import com.psybrainy.menu.model.CategoryEntity;
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
    private CategoryCrudRepository categoryRepo;

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

        ProductEntity productEntity = repo.findById(productId)
                .orElseThrow(()-> new NotFoundException(ConstantExceptionMessage.MSG_NOT_FOUND_PRODUCT));

        return entityToDto(productEntity);
    }

    @Override
    public List<ProductResponce> getByCategory(Long categoryId) throws NotFoundException{
        List<ProductEntity> productEntityList = repo.findByCategory(categoryId).orElseThrow(()-> new NotFoundException(ConstantExceptionMessage.MSG_NOT_FOUND_PRODUCT_CATEGORY));

        return productEntityList
                .stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponce save(ProductRequest productRequest) throws NotFoundException{

        if (productRequest.getName().isEmpty()){
            throw new BadRequestException(ConstantExceptionMessage.MSG_BAD_REQUEST_PRODUCT_NAME);
        }else if (productRequest.getCategory().isEmpty()){
            throw new BadRequestException(ConstantExceptionMessage.MSG_NOT_FOUND_PRODUCT_CATEGORY_NULL);
        }else if (productRequest.getPrice() == null){
            throw new BadRequestException(ConstantExceptionMessage.MSG_BAD_REQUEST_PRODUCT_PRICE);
        }

        ProductEntity productEntity = dtoToEntity(productRequest);
        productEntity.setCategory(categoryRepo.findByName(productRequest.getCategory()).orElseThrow(()-> new NotFoundException(ConstantExceptionMessage.MSG_NOT_FOUND_PRODUCT_CATEGORY_NAME)));

        ProductEntity productSave = repo.save(productEntity);

        return entityToDto(productSave);
    }

    @Override
    public ProductResponce update(Long idProduct, ProductRequest productRequest) throws NotFoundException{

        ProductEntity productValid = repo.findById(idProduct).orElseThrow(()-> new NotFoundException(ConstantExceptionMessage.MSG_NOT_FOUND_PRODUCT));
        ProductEntity productEntity = dtoToEntity(productRequest);
        productEntity.setIdProduct(idProduct);


        if (productRequest.getName().isEmpty()){
            productEntity.setName(productValid.getName());
        }else if (productRequest.getCategory().isEmpty()){
            productEntity.setCategory(productValid.getCategory());
        }else if (productRequest.getDescription().isEmpty()){
            productEntity.setDescription(productValid.getDescription());
        }else if (productRequest.getPrice() == null){
            productEntity.setPrice(productValid.getPrice());
        }else if (productRequest.getPhoto().isEmpty()){
            productEntity.setPhoto(productValid.getPhoto());
        }

        CategoryEntity categoryEntity = categoryRepo.findByName(productRequest.getCategory())
                .orElseThrow(()-> new NotFoundException(ConstantExceptionMessage.MSG_NOT_FOUND_PRODUCT_CATEGORY_NAME));

        productEntity.setCategory(categoryEntity);

        return entityToDto(repo.save(productEntity));
    }

    @Override
    public boolean delete(Long productId) {
        return repo.findById(productId).map(productRequest -> {
            repo.deleteById(productId);
            return true;
        }).orElse(false);
    }


    public ProductResponce entityToDto(ProductEntity product){
        mapper.map(product.getCategory(), CategoryResponce.class);
        return mapper.map(product, ProductResponce.class);
    }

    public ProductEntity dtoToEntity(ProductRequest product){
        return mapper.map(product, ProductEntity.class);
    }
}
