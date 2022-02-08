package com.userssecurity.autenticsecurityusersdemo.mapper;

import com.userssecurity.autenticsecurityusersdemo.dtos.UserDTO;
import com.userssecurity.autenticsecurityusersdemo.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    User toModel(UserDTO userDTO) ;
    UserDTO toDTO(User user) ;
}
