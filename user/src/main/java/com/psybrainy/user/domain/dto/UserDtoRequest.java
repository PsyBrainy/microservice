package com.psybrainy.user.domain.dto;

import lombok.Data;

@Data
public class UserDtoRequest {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String photo;
}
