package com.example.simple_marketplace.service.mapper;

import com.example.simple_marketplace.dto.ImageDto;
import com.example.simple_marketplace.modul.Image;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
 public abstract class ImageMapper {
   @Mapping(target = "createdAt",ignore = true)
    @Mapping(target = "updatedAt",ignore = true)
    @Mapping(target = "deletedAt",ignore = true)
    public abstract ImageDto toDto(Image image);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void updateToDtoFromEntity(@MappingTarget Image image, ImageDto imageDto);


}
