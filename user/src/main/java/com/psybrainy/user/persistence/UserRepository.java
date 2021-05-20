package com.psybrainy.user.persistence;

import com.psybrainy.user.domain.dto.UserDtoRequest;
import com.psybrainy.user.domain.dto.UserDtoResponse;
import com.psybrainy.user.domain.repository.IUserRepo;
import com.psybrainy.user.persistence.crud.RoleCrudRepo;
import com.psybrainy.user.persistence.crud.UserCrudRepo;
import com.psybrainy.user.persistence.entity.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository implements IUserRepo {

    @Autowired
    private UserCrudRepo userRepo;

    @Autowired
    private RoleCrudRepo roleRepo;

    @Override
    public UserDtoResponse save(UserDtoRequest userRequest){

        UserEntity userEntity = dtoToEntity(userRequest);

        userEntity.setRole(roleRepo.findByName("ROLE_USER"));

        UserEntity userSave = userRepo.save(userEntity);

        return entityToDto(userSave);
    }

    @Override
    public UserDtoResponse findByEmail(String email) {

        UserEntity userEntity = userRepo.findByEmail(email);

        return entityToDto(userEntity);
    }


    public UserDtoResponse entityToDto(UserEntity user){
        ModelMapper mapper = new ModelMapper();
        return mapper.map(user, UserDtoResponse.class);
    }

    public UserEntity dtoToEntity(UserDtoRequest userDTORequest){
        ModelMapper mapper = new ModelMapper();
        return mapper.map(userDTORequest, UserEntity.class);
    }
}
