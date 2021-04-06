package com.psybrainy.product.presistence.crud;

import com.psybrainy.product.presistence.entity.CategoryEntity;
import com.psybrainy.product.presistence.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductCrudRepository extends CrudRepository<ProductEntity, Long> {

    Optional<List<ProductEntity>> findByCategory(CategoryEntity categoryEntity);
}
