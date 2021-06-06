package com.psybrainy.menu.crud;

import com.psybrainy.menu.model.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductCrudRepository extends JpaRepository<ProductEntity, Long> {

    Optional<List<ProductEntity>> findByCategory(Long categoryId);
}
