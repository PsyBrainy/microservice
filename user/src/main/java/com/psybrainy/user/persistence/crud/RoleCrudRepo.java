package com.psybrainy.user.persistence.crud;

import com.psybrainy.user.persistence.entity.RoleEntity;
import org.springframework.data.repository.CrudRepository;

public interface RoleCrudRepo extends CrudRepository<RoleEntity, Long> {

    RoleEntity findByName(String name);
}
