package com.example.simple_marketplace.service.mapper;

import com.example.simple_marketplace.dto.BasketDto;
import com.example.simple_marketplace.modul.Basket;
import org.mapstruct.*;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public abstract class BasketMapper {
    @Mapping(target = "createdAt",ignore = true)
    @Mapping(target = "updatedAt",ignore = true)
    @Mapping(target = "deletedAt",ignore = true)
    public abstract BasketDto toDto(Basket basket);
    @Mapping(target = "id",ignore = true)
    public abstract Basket toEntity(BasketDto dto);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void updateToDtoFromEntity(BasketDto dto,@MappingTarget Basket basket);

}
