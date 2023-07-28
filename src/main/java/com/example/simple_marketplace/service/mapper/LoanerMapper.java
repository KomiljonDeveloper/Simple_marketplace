package com.example.simple_marketplace.service.mapper;

import com.example.simple_marketplace.dto.LoanerDto;
import com.example.simple_marketplace.modul.Loaner;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public abstract class LoanerMapper {
    @Mapping(target = "createdAt",ignore = true)
    @Mapping(target = "updatedAt",ignore = true)
    @Mapping(target = "deletedAt",ignore = true)
    public abstract LoanerDto toDto(Loaner loaner);
    @Mapping(target = "id")
    public abstract Loaner toEntity(LoanerDto loanerDto);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void updateToDtoFromEntity(LoanerDto dto,@MappingTarget Loaner entity);
}
