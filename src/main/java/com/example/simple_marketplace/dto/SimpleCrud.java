package com.example.simple_marketplace.dto;

public interface SimpleCrud<D,T> {
   ResponseDto<D> create(D dto);
   ResponseDto<D> get(T id);
   ResponseDto<D> update(D dto,T id);
   ResponseDto<D> delete(T id);
}
