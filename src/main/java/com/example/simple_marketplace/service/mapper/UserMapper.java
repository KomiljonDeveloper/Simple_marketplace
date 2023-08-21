package com.example.simple_marketplace.service.mapper;

import com.example.simple_marketplace.dto.ReportsDto;
import com.example.simple_marketplace.dto.UserDto;
import com.example.simple_marketplace.modul.Reports;
import com.example.simple_marketplace.modul.User;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring",imports = LocalDateTime.class)
public abstract class UserMapper {
    @Mapping(target = "id",ignore = true)
    public abstract User toEntity(UserDto dto);
    @Mapping(target = "createdAt",ignore = true)
    @Mapping(target = "updatedAt",ignore = true)
    @Mapping(target = "deletedAt",ignore = true)
    public abstract UserDto toDto(User reports);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public void updateToDtoFromEntity(UserDto dto,@MappingTarget User reports) {

    }
}
