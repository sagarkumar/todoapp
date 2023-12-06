package com.sagarpr.todoapp.services;

import com.sagarpr.todoapp.dto.UserRegistrationDto;
import com.sagarpr.todoapp.models.RoleEntity;
import com.sagarpr.todoapp.models.UserEntity;
import com.sagarpr.todoapp.repository.RoleEntityRepository;
import com.sagarpr.todoapp.repository.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
public class UserEntityServiceImpl implements UserEntityService{

    private UserEntityRepository userEntityRepository;
    private RoleEntityRepository roleEntityRepository;

    @Autowired
    public UserEntityServiceImpl(UserEntityRepository userEntityRepository, RoleEntityRepository roleEntityRepository) {
        this.userEntityRepository = userEntityRepository;
        this.roleEntityRepository = roleEntityRepository;
    }

    @Override
    public void saveUser(UserRegistrationDto userRegistrationDto) {
        UserEntity userEntity = UserEntity.builder()
                .username(userRegistrationDto.getUsername())
                .email(userRegistrationDto.getEmail())
                .password(userRegistrationDto.getPassword()).build();

        RoleEntity roleEntity = this.roleEntityRepository.findByName("USER");
        userEntity.setRoleEntityList(Arrays.asList(roleEntity));
        this.userEntityRepository.save(userEntity);
    }

    @Override
    public boolean isExistingUser(UserRegistrationDto userRegistrationDto) {
        Optional<UserEntity> userEntity = this.userEntityRepository.findByEmail(userRegistrationDto.getEmail());
        if(userEntity.isPresent()){
            if(userEntity.get().getEmail() != null && !userEntity.get().getEmail().isEmpty()){
                return true;
            }
        }
        return false;
    }
}
