package com.psybrainy.user.domain.service;

import com.psybrainy.user.domain.dto.UserDtoRequest;
import com.psybrainy.user.domain.dto.UserDtoResponse;
import org.springframework.stereotype.Service;

@Service
public interface IUserService {

    UserDtoResponse save(UserDtoRequest userRequest);
}
