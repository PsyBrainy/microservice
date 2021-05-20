package com.psybrainy.user.persistence.crud;

import com.psybrainy.user.persistence.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserCrudRepo extends CrudRepository<UserEntity, Long> {

    UserEntity findByEmail(String email);
}
