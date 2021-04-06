package com.psybrainy.product.presistence.crud;

import com.psybrainy.product.presistence.entity.CategoryEntity;
import org.springframework.data.repository.CrudRepository;

public interface CategoryCrudRepository extends CrudRepository<CategoryEntity, Long> {
}
