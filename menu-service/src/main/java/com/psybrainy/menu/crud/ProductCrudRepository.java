package com.psybrainy.menu.crud;

import com.psybrainy.menu.model.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCrudRepository extends JpaRepository<ProductEntity, Long> {
}
