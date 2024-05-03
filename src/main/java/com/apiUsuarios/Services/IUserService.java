package com.apiUsuarios.Services;

import com.apiUsuarios.Models.Dto.UserEntityDTO;
import com.apiUsuarios.Models.UserEntity;

import java.util.List;

public interface IUserService {
    UserEntity create(UserEntityDTO userDTO);
    UserEntity update(Long id, UserEntityDTO userDTO);
    String delete(Long id);
    UserEntity getById(Long id);
    List<UserEntity> getAll();
}
