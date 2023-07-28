package com.example.simple_marketplace.service.mapper;

import com.example.simple_marketplace.dto.ProductDto;
import com.example.simple_marketplace.modul.Product;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public abstract class ProductMapper {
    @Mapping(target = "createdAt",ignore = true)
    @Mapping(target = "updatedAt",ignore = true)
    @Mapping(target = "deletedAt",ignore = true)
 public abstract ProductDto toDto(Product product);
 @Mapping(target = "productId",ignore = true)
 public abstract Product toEntity(ProductDto dto);
 @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
 public abstract void updateToDtoFromEntity(@MappingTarget Product product, ProductDto productDto);
}
