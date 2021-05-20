package com.psybrainy.user.domain.service.imp;

import com.psybrainy.user.domain.dto.UserDtoRequest;
import com.psybrainy.user.domain.dto.UserDtoResponse;
import com.psybrainy.user.domain.repository.IUserRepo;
import com.psybrainy.user.domain.service.IUserService;
import com.psybrainy.user.persistence.crud.UserCrudRepo;
import com.psybrainy.user.persistence.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements IUserService, UserDetailsService {

    @Autowired
    private IUserRepo userRepo;

    @Autowired
    private UserCrudRepo userCrud;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public UserDtoResponse save(UserDtoRequest userRequest){

        userRequest.setPassword(encoder.encode(userRequest.getPassword()));

        return userRepo.save(userRequest);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UserEntity userEntity = userCrud.findByEmail(email);

        List<GrantedAuthority> rol = new ArrayList<>();
        rol.add(new SimpleGrantedAuthority(userEntity.getRole().getName()));

        return new User(userEntity.getEmail(), userEntity.getPassword(), rol);
    }
}
