package com.example.simple_marketplace.service.mapper;

import com.example.simple_marketplace.dto.ReportsDto;
import com.example.simple_marketplace.modul.Reports;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public abstract class ReportsMapper {
    @Mapping(target = "id",ignore = true)
    public abstract Reports toEntity(ReportsDto dto);
    @Mapping(target = "createdAt",ignore = true)
    @Mapping(target = "updatedAt",ignore = true)
    @Mapping(target = "deletedAt",ignore = true)
    public abstract ReportsDto toDto(Reports reports);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public void updateToDtoFromEntity(ReportsDto dto,@MappingTarget Reports reports) {

    }
}
