package com.example.simple_marketplace.dto;

import org.springframework.data.domain.Page;

import java.util.List;

public interface SimpleCrud<D,T> {
   ResponseDto<D> create(D dto);
   ResponseDto<D> get(T id);
   ResponseDto<D> update(D dto,T id);
   ResponseDto<D> delete(T id);
   ResponseDto<Page<D>> getAllByPage(Integer page, Integer size);
}
