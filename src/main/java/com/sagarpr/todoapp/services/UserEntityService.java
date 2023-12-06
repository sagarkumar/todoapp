package com.sagarpr.todoapp.services;

import com.sagarpr.todoapp.dto.UserRegistrationDto;

public interface UserEntityService {

     void saveUser(UserRegistrationDto userRegistrationDto);

     boolean isExistingUser(UserRegistrationDto userRegistrationDto);
}
