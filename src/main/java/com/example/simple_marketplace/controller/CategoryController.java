package com.example.simple_marketplace.controller;

import com.example.simple_marketplace.dto.CategoryDto;
import com.example.simple_marketplace.dto.ResponseDto;
import com.example.simple_marketplace.dto.SimpleCrud;
import com.example.simple_marketplace.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("category/")
@RequiredArgsConstructor
public class CategoryController implements SimpleCrud<CategoryDto,Integer> {
    private final CategoryService categoryService;
    @Override
    @PostMapping("create")
    public ResponseDto<CategoryDto> create(@RequestBody CategoryDto dto) {
        return this.categoryService.create(dto);
    }

    @Override
    @GetMapping("get/{id}")
    public ResponseDto<CategoryDto> get(@PathVariable Integer id) {
        return this.categoryService.get(id);
    }

    @Override
    @PutMapping("update/{id}")
    public ResponseDto<CategoryDto> update(@RequestBody CategoryDto dto,@PathVariable Integer id) {
        return this.categoryService.update(dto, id);
    }

    @Override
    @DeleteMapping("delete/{id}")
    public ResponseDto<CategoryDto> delete(@PathVariable Integer id) {
        return this.categoryService.delete(id);
    }
}
