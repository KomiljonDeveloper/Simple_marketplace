package com.example.simple_marketplace.service.mapper;

import com.example.simple_marketplace.dto.ForeignDebtDto;
import com.example.simple_marketplace.modul.ForeignDebt;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public abstract class ForeignDebtMapper {
    @Mapping(target = "id",ignore = true )
   public abstract ForeignDebt toEntity(ForeignDebtDto foreignDebtDto);
    @Mapping(target = "createdAt",ignore = true)
    @Mapping(target = "updatedAt",ignore = true)
    @Mapping(target = "deletedAt",ignore = true)
    public abstract ForeignDebtDto toDto(ForeignDebt foreignDebt);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void updateToDtoFromEntity(ForeignDebtDto foreignDebtDto,@MappingTarget ForeignDebt foreignDebt);


}
