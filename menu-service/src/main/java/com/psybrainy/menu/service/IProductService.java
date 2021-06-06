package com.psybrainy.menu.service;

import com.psybrainy.menu.dto.ProductRequest;
import com.psybrainy.menu.dto.ProductResponce;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IProductService {

    List<ProductResponce> getAll();
    ProductResponce getproduct(Long productId) throws NotFoundException;
    List<ProductResponce> getByCategory(Long categoryId) throws NotFoundException;
    ProductResponce save(ProductRequest productRequest)throws NotFoundException;
    ProductResponce update(Long idProduct, ProductRequest productRequest) throws NotFoundException;
    boolean delete(Long productId);

}
