package com.hitit.mapper;

import com.hitit.dto.UserDto;
import com.hitit.entity.UserDetail;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    private final ModelMapper modelMapper;

    public UserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public UserDetail convertToEntity(UserDto userDTO) {
        return modelMapper.map(userDTO, UserDetail.class);
    }

    public List<UserDetail> convertToEntityList(List<UserDto> userDtos) {
        return userDtos.stream().map(this::convertToEntity).collect(Collectors.toList());
    }

}
