package com.apiUsuarios.Services.Implementation;

import com.apiUsuarios.Models.Dto.UserEntityDTO;
import com.apiUsuarios.Models.ERole;
import com.apiUsuarios.Models.RoleEntity;
import com.apiUsuarios.Models.UserEntity;
import com.apiUsuarios.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.apiUsuarios.Services.IUserService;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserEntity create(UserEntityDTO userDTO) {
        Set<RoleEntity> roles = userDTO.getRoles().stream()
                .map(role -> RoleEntity.builder()
                        .name(ERole.valueOf(role))
                        .build())
                .collect(Collectors.toSet());

        UserEntity userEntity = UserEntity.builder()
                .username(userDTO.getUsername())
                .email(userDTO.getEmail())
                .password(this.passwordEncoder.encode(userDTO.getPassword()))
                .roles(roles)
                .build();
        return this.userRepository.save(userEntity);
    }

    @Override
    public UserEntity update(Long id, UserEntityDTO userDTO) {
        Set<RoleEntity> roles = userDTO.getRoles().stream()
                .map(role -> RoleEntity.builder()
                        .name(ERole.valueOf(role))
                        .build())
                .collect(Collectors.toSet());

        UserEntity userEntity = this.userRepository.findById(id).get();
        userEntity.setUsername(userDTO.getUsername());
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setPassword(this.passwordEncoder.encode(userDTO.getPassword()));
        userEntity.setRoles(roles);
        return this.userRepository.save(userEntity);
    }

    @Override
    public String delete(Long id) {
        this.userRepository.deleteById(id);
        return "Se elimino el usuario " + id;
    }

    @Override
    public UserEntity getById(Long id) {
        return this.userRepository.findById(id).get();
    }

    @Override
    public List<UserEntity> getAll() {
        return this.userRepository.findAll();
    }
}
