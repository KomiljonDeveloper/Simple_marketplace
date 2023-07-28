package com.example.simple_marketplace.service.mapper;

import com.example.simple_marketplace.dto.CategoryDto;
import com.example.simple_marketplace.dto.EmployeesDto;
import com.example.simple_marketplace.modul.Category;
import com.example.simple_marketplace.modul.Employees;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class EmployeesMapper {
    @Mapping(target = "createdAt",ignore = true)
    @Mapping(target = "updatedAt",ignore = true)
    @Mapping(target = "deletedAt",ignore = true)
    public abstract EmployeesDto toDto(Employees employee);
    @Mapping(target = "id",ignore = true)
    public abstract Employees toEntity(EmployeesDto employeeDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void updateToDtoFromEntity(EmployeesDto dto,@MappingTarget Employees employee);



}
