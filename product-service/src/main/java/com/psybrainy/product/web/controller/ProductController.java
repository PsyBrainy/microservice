package com.psybrainy.product.web.controller;

import com.psybrainy.product.domain.CategoryRequest;
import com.psybrainy.product.domain.ProductRequest;
import com.psybrainy.product.domain.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping
    public ResponseEntity<List<ProductRequest>> getAll(){
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductRequest> getproduct(@PathVariable("id") Long productId){
        return service.getproduct(productId)
                .map(productRequest -> new ResponseEntity<>(productRequest,HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<ProductRequest>> getByCategoryId(@PathVariable("categoryId") long categoryId) {
        return service.getByCategoryId(categoryId)
                .map(productRequests -> new ResponseEntity<>(productRequests, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/save")
    public  ResponseEntity<ProductRequest> save(@RequestBody ProductRequest productRequest){
        return new ResponseEntity<>(service.save(productRequest), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public  ResponseEntity delete(@PathVariable("id") Long productId){
        if (service.delete(productId)) {
            return new ResponseEntity(HttpStatus.OK);
        }else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
