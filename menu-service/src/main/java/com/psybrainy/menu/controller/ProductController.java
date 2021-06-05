package com.psybrainy.menu.controller;

import com.psybrainy.menu.dto.ProductRequest;
import com.psybrainy.menu.dto.ProductResponce;
import com.psybrainy.menu.service.IProductService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private IProductService productService;


    @GetMapping
    public ResponseEntity<List<ProductResponce>> getAll(){
        return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductRequest> getproduct(@PathVariable("id") Long productId) throws NotFoundException {
        return new ResponseEntity(productService.getproduct(productId), HttpStatus.OK);
    }

    @PostMapping("/save")
    public  ResponseEntity<ProductResponce> save(@RequestBody ProductRequest productRequest)throws NotFoundException{
        return new ResponseEntity<>(productService.save(productRequest), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<ProductResponce> update(@PathVariable("id")Long idProduct , @RequestBody ProductRequest productRequest)throws NotFoundException{
        return new ResponseEntity<>(productService.update(idProduct ,productRequest), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public  ResponseEntity delete(@PathVariable("id") Long productId){
        if (productService.delete(productId)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
