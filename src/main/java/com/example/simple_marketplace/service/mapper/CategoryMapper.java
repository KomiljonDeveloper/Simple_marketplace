package com.example.simple_marketplace.service.mapper;

import com.example.simple_marketplace.dto.CategoryDto;
import com.example.simple_marketplace.modul.Category;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class CategoryMapper {
    @Mapping(target = "createdAt",ignore = true)
    @Mapping(target = "updatedAt",ignore = true)
    @Mapping(target = "deletedAt",ignore = true)
    public abstract CategoryDto toDto(Category category);
    @Mapping(target = "categoryId",ignore = true)
    public abstract Category toEntity(CategoryDto categoryDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void updateToDtoFromEntity(CategoryDto dto,@MappingTarget Category category);
}
