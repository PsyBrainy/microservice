package com.psybrainy.user.domain.repository;

import com.psybrainy.user.domain.dto.UserDtoRequest;
import com.psybrainy.user.domain.dto.UserDtoResponse;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepo {

    UserDtoResponse save(UserDtoRequest userRequest);
    UserDtoResponse findByEmail(String email);
}
